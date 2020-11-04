package fly.frontend.interceptor;

import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserVO user = (UserVO) request.getSession().getAttribute(UserService.LOGIN_KEY);
        if (user != null) {
            return true;
        }
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            throw new Exception("请先登录");
        }

        StringBuilder url = new StringBuilder();
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getServletPath());
        if (request.getQueryString() != null) {
            url.append("?").append(request.getQueryString());
        }
        response.sendRedirect("/user/login?redirect=" + URLEncoder.encode(url.toString(), "UTF-8"));
        return false;
    }
}
