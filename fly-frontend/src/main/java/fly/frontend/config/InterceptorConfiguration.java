package fly.frontend.config;

import fly.frontend.interceptor.ExceptionResponseInterceptor;
import fly.frontend.interceptor.UserAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


public class InterceptorConfiguration {

    @Resource
    private UserAuthInterceptor userAuthInterceptor;

    @Resource
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
                        .addPathPatterns("/userMessage/**")
                        .excludePathPatterns("/user/index/{id}")
                        .excludePathPatterns("/user/login")
                        .excludePathPatterns("/user/register")
                        .excludePathPatterns("/user/forget")
                        .excludePathPatterns("/post/detail/{id}");
                registry.addInterceptor(exceptionResponseInterceptor)
                        .addPathPatterns("/**");
            }
        };
    }
}
