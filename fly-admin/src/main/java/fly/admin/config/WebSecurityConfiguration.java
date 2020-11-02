package fly.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.admin.entity.model.AdminPermission;
import fly.admin.entity.model.AdminRolePermission;
import fly.admin.entity.model.AdminUser;
import fly.admin.entity.model.AdminUserRole;
import fly.admin.entity.vo.ResultVO;
import fly.admin.exception.NotAllowAccessException;
import fly.admin.repository.AdminPermissionRepository;
import fly.admin.repository.AdminRolePermissionRepository;
import fly.admin.repository.AdminUserRepository;
import fly.admin.repository.AdminUserRoleRepository;
import fly.admin.service.auth.AdminPermissionService;
import fly.admin.service.auth.AdminUserService;
import fly.admin.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AdminUserRepository adminUserRepository;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminUserRoleRepository adminUserRoleRepository;

    @Resource
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Resource
    private AdminPermissionRepository adminPermissionRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Map<String, Set<String>> getPermissions(AdminUser user) {
        List<AdminUserRole> items = adminUserRoleRepository.findByUserId(user.getId());

        List<AdminRolePermission> permissionList = adminRolePermissionRepository.findByRoleIdIn(items.stream().map(AdminUserRole::getRoleId).collect(Collectors.toList()));

        List<Integer> permissionIds = permissionList.stream().map(AdminRolePermission::getPermissionId).collect(Collectors.toList());

        List<AdminPermission> permissions = adminPermissionRepository.findByIdIn(permissionIds);

        Map<String, Set<String>> map = new HashMap<>();
        permissions.forEach(permission -> {
            String[] methods;
            if ("".equals(permission.getHttpMethod())) {
                methods = ORIGINS;
            } else {
                methods = permission.getHttpMethod().split(AdminPermissionService.METHOD_SPLIT);
            }

            for (String method : methods) {
                String[] uris = permission.getHttpPath().split(AdminPermissionService.PATH_SPLIT);
                for (String uri : uris) {
                    map.computeIfAbsent(method, k -> new HashSet<>()).add(uri);
                }
            }
        });
        return map;
    }

    public boolean hasPermission(AdminUser user, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        Map<String, Set<String>> permissions = getPermissions(user);

        AtomicBoolean hasPermission = new AtomicBoolean(false);

        Optional.ofNullable(permissions.get(requestMethod)).orElse(new HashSet<>()).forEach((uri) -> {
            boolean matches = FileSystems.getDefault()
                    .getPathMatcher("glob:" + uri)
                    .matches(Paths.get(requestURI));
            if (matches) {
                hasPermission.set(true);
            }
        });
        return hasPermission.get();
    }


    /**
     * Spring Security (CORS)跨域资源访问配置
     * https://www.cnblogs.com/famary/p/10336223.html
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //允许跨域访问
                .cors()
                .and()
                //基于token，所以不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();

        httpSecurity.csrf().disable();//暂时禁用csrf

        httpSecurity.addFilterBefore(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                try {
                    //请求是否带有token
                    String token = request.getHeader("X-Token");
                    //验证token是否有效 -> 通过token获取用户信息 -> 如果有效保存用户的相关信息
                    if (token != null && JwtUtil.checkSign(token)) {
                        String username = JwtUtil.getUserId(token);
                        log.info("header token username:" + username);

                        AdminUser adminUser = adminUserRepository.findByUsername(username);

                        fly.admin.service.UserDetails details = new fly.admin.service.UserDetails(adminUser, adminUserService.getAuthorities(adminUser));
                        log.info(details.getAuthorities().toString());

                        if (!hasPermission(adminUser, request)) {
                            throw new NotAllowAccessException("您没有操作权限，不能进行当前操作");
                        }

                        String uri = request.getRequestURI();
                        log.info("request uri:" + uri);

                        //userDetailsService
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                details, null, details.getAuthorities()
                        );

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //设置用户登录状态
                        log.info("authenticated user {}, setting security context", token);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    filterChain.doFilter(request, response);
                } catch (RuntimeException ex) {
                    response.getWriter()
                            .print(
                                    new ObjectMapper()
                                            .writeValueAsString(
                                                    ResultVO.builder().code("exception").message(ex.getMessage()).build()
                                            )
                            );
                }
            }
        }, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        //忽略拦截
        webSecurity.ignoring()
                .antMatchers("/swagger-ui.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList(ORIGINS));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
