package fly.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"};

//    *
//     * 跨域设置
//     * @param registry
//
//
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                //是否发送Cookie
//                .allowCredentials(true)
//                //放行哪些原始域
//                .allowedOrigins("*")
//                .allowedMethods(ORIGINS)
//                .allowedHeaders("*")
//                .exposedHeaders("Content-Type","X-Requested-With","accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers");
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource(){
//        return httpServletRequest -> {
//            CorsConfiguration cfg = new CorsConfiguration();
//            cfg.addAllowedHeader("*");
//            cfg.addAllowedMethod("*");
//            cfg.addAllowedOrigin("http://localhost:9528");
//            cfg.setAllowCredentials(true);
//            cfg.checkOrigin("http://localhost:9528");
//            return cfg;
//        };
//    }

    /**
     * Spring Security (CORS)跨域资源访问配置
     * https://www.cnblogs.com/famary/p/10336223.html
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();//允许跨域访问

        httpSecurity.formLogin()//允许表单登录
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {//设置登录成功之后的操作
                    httpServletResponse.getWriter().print("{\"code\":\"success\",\"data\":{\"token\":\"admin-token\"}}");
                });

        //https://www.cnblogs.com/l1ng14/p/13530416.html
        httpSecurity.csrf().disable();//暂时禁用csrf
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedHeaders(Arrays.asList("User-Agent","Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers","Host"));
        configuration.addAllowedHeader("*");
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList(ORIGINS));
//        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
