package fly.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE"};

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
     *  Spring Security (CORS)跨域资源访问配置
     *  https://www.cnblogs.com/famary/p/10336223.html
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();//允许跨域访问
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","X-Requested-With","accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList(ORIGINS));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
