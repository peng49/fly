package fly.admin.config;

import fly.admin.entity.model.AdminUser;
import fly.admin.repository.AdminUserRepository;
import fly.admin.service.auth.AdminUserService;
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
import java.util.Arrays;
import java.util.Collections;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean hasPermission(AdminUser user,HttpServletRequest request)
    {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        return true;
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
                .antMatchers("/api/**")
                .authenticated()
//                .expressionHandler()
                .and()
                .formLogin()//允许表单登录
                .successHandler((request, response, authentication) -> {//设置登录成功之后的操作
//                    UserDetails details = (UserDetails) authentication.getDetails();

                    response.getWriter().print("{\"code\":\"success\",\"data\":{\"token\":\"admin-token\"}}");
                }).failureHandler((request, response, exception) -> {
                    log.debug(exception.getMessage());
                    response.getWriter().println("{\"code\":\"exception\",\"message\":\"login fail\"" + exception.getMessage() + "}");
                });

        //https://www.cnblogs.com/l1ng14/p/13530416.html
        httpSecurity.csrf().disable();//暂时禁用csrf

        httpSecurity.addFilterBefore(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                //请求是否带有token
                String token = request.getHeader("X-Token");
                if (token != null) {
                    log.info("header token:" + token);
                    //todo 验证token是否有效 -> 通过token获取用户信息 -> 如果有效保存用户的相关信息

                    AdminUser adminUser = adminUserRepository.findByUsername("admin");

                    fly.admin.service.UserDetails details = new fly.admin.service.UserDetails(adminUser, adminUserService.getAuthorities(adminUser));
                    log.info(details.getAuthorities().toString());

                    if(!hasPermission(adminUser,request)){
                        throw new RuntimeException("not allow access");
                    }

                    String uri = request.getRequestURI();
                    log.info("request uri:"+uri);

                    //userDetailsService
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            details, null, details.getAuthorities()
                    );
                    log.info(authentication.toString());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //设置用户登录状态
                    log.info("authenticated user {}, setting security context", token);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
            }
        }, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity){
        //忽略拦截 https://www.cnblogs.com/lenve/p/11242055.html
        webSecurity.ignoring().antMatchers("/swagger-ui.html");
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
