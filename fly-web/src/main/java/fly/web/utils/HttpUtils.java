package fly.web.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import fly.web.entity.vo.UserVO;
import fly.web.service.UserService;
import fly.web.service.impl.UserServiceImpl;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class HttpUtils {
    public static String getCurrentUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort()
                + request.getServletPath();
        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();
        }
        return url;
    }


    public static String setUrlParam(String url, String name, String value) {
        if (url.matches(".*[&|?]" + name + "=.*")) {
            url = url.replaceAll(name + "=.*?(&|$)", name + "=" + value + "$1");
        } else {
            int i = url.indexOf("?");
            if (i > 0) {
                url = url + "&" + name + "=" + value;
            } else {
                url = url + "?" + name + "=" + value;
            }
        }
        return url;
    }

    public static boolean isMobile(String userAgent) {
        return Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);
    }

    public static Object success() {
        return HttpUtils.response("success", "OK");
    }

    public static Object success(Object data) {
        if (data instanceof IPage) {
            HashMap<Object, Object> hash = new HashMap<>();
            hash.put("total", ((IPage<?>) data).getTotal());
            hash.put("page", ((IPage<?>) data).getCurrent());
            hash.put("pageSize", ((IPage<?>) data).getSize());
            hash.put("rows", ((IPage<?>) data).getRecords());
            return HttpUtils.response("success", "OK", hash);
        }
        return HttpUtils.response("success", "OK", data);
    }

    public static Object fail(String message) {
        return HttpUtils.response("fail", message);
    }

    public static HashMap<Object, Object> response(String code, String message) {
        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("code", code);
        resp.put("message", message);
        return resp;
    }

    public static Object response(String code, String message, Object data) {
        HashMap<Object, Object> resp = HttpUtils.response(code, message);
        resp.put("data", data);
        return resp;
    }

    public static UserVO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User securityUser = (User) authentication.getPrincipal();
        if (securityUser == null) {
            throw new RuntimeException("请先登录");
        }

        ServletContext context = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        UserService userService = webApplicationContext.getBean(UserService.class);

        fly.web.entity.model.User user = userService.getByUsername(securityUser.getUsername());

        return UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .isAdmin(user.getIsAdmin())
                .avatar(user.getAvatar())
                .build();
    }

    /**
     * 选择模板页面
     *
     * @param viewName
     * @param userAgent
     * @param view
     */
    public static void selectViewName(String viewName, String userAgent, ModelAndView view) {
        view.setViewName(viewName);
        if (isMobile(userAgent)) {
            view.setViewName("wap/" + viewName.replaceFirst("^/", ""));
        }
    }
}
