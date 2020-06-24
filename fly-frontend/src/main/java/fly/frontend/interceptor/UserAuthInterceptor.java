package fly.frontend.interceptor;

import fly.frontend.entity.User;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(UserService.LOGIN_KEY);
        if (user != null) {
            return true;
        }

        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            throw new Exception("请先登录");
        }
        response.sendRedirect("/user/login");
        return false;
    }
}
