package fly.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Security (CORS)跨域资源访问配置
     * https://www.cnblogs.com/famary/p/10336223.html
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();//允许跨域访问

        httpSecurity.formLogin()//允许表单登录
                .successHandler((request, response, authentication) -> {//设置登录成功之后的操作
                    response.getWriter().print("{\"code\":\"success\",\"data\":{\"token\":\"admin-token\"}}");
                }).failureHandler((request, response, exception) -> {
                    log.debug(exception.getMessage());
                    response.getWriter().println("{\"code\":\"exception\",\"message\":\"login fail\""+exception.getMessage()+"}");
                }
        );

        //https://www.cnblogs.com/l1ng14/p/13530416.html
        httpSecurity.csrf().disable();//暂时禁用csrf

//        httpSecurity.authorizeRequests()
//                .antMatchers("/api/users/**")
//                .hasAnyAuthority("administrator");
    }

//    @Override
//    public void configure(WebSecurity webSecurity){
//        //忽略拦截 https://www.cnblogs.com/lenve/p/11242055.html
//        webSecurity.ignoring().antMatchers("/api/ignoring");
//    }

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
