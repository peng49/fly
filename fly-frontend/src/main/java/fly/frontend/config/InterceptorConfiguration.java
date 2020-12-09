package fly.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

//@Configuration
public class InterceptorConfiguration {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

    private HandlerInterceptorAdapter getHandlerInterceptorAdapter()
    {
        class Inner extends HandlerInterceptorAdapter {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (errorCodeList.contains(response.getStatus())) {
//                    ModelAndView mv = new ModelAndView("page/404");
//                    mv.setStatus(HttpStatus.NOT_FOUND);
//                    throw new ModelAndViewDefiningException(mv);
//                    request.setAttribute("code",404);
//                    response.sendRedirect("/error/404");
                    request.getRequestDispatcher("/error/404").forward(request,response);
                    return false;
                }
                return super.preHandle(request, response, handler);
            }
        }
        return new Inner();
    }

    @Bean
    public WebMvcConfigurer registerInterceptor() {
        return new WebMvcConfigurer() {
            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //添加一个针对所有路径的拦截器，处理异常响应
                registry.addInterceptor(getHandlerInterceptorAdapter())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/error/**");
            }
        };
    }
}
