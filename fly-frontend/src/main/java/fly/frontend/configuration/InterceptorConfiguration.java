package fly.frontend.configuration;

import fly.frontend.interceptor.ExceptionResponseInterceptor;
import fly.frontend.interceptor.UserAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration {

    @Autowired
    private UserAuthInterceptor userAuthInterceptor;

    @Autowired
    private ExceptionResponseInterceptor exceptionResponseInterceptor;

    @Bean
    public WebMvcConfigurer registerInterceptor() {
        return new WebMvcConfigurer() {
            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(userAuthInterceptor)
                        .addPathPatterns("/user/**")
                        .addPathPatterns("/post/**")
                        .excludePathPatterns("/user/index/{id}")
                        .excludePathPatterns("/user/login")
                        .excludePathPatterns("/user/register")
                        .excludePathPatterns("/post/detail/{id}");
                registry.addInterceptor(exceptionResponseInterceptor)
                        .addPathPatterns("/**");
            }
        };
    }
}
