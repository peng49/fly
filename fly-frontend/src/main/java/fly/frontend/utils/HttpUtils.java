package fly.frontend.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class HttpUtils {
    public static String setParameter(HttpServletRequest request, String name, String value) {
        String path;
        if (request.getParameter(name) == null) {
            path = request.getQueryString() == null ? name + "=" + value : request.getQueryString() + "&" + name + "=" + value;
        } else {
            String queryString = request.getQueryString();
            path = queryString.replaceAll(name + "=.*?(&|$)", name + "=" + value + "$1");
        }
        return path;
    }

    public static boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }

        return Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);
    }
}
