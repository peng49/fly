package fly.frontend.configuration;

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

    @Bean
    public WebMvcConfigurer authInterceptor()
    {
        return new WebMvcConfigurer() {
            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(userAuthInterceptor)
                        .addPathPatterns("/user/**")
                        .excludePathPatterns("/user/index/{id}")
                        .excludePathPatterns("/user/login")
                        .excludePathPatterns("/user/register");
            }
        };
    }

}
