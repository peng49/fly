package fly.frontend.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class HttpUtils {

    public static String getCurrentUrl(HttpServletRequest request) {
        String url;
        url = request.getScheme() + "://" + request.getServerName()
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

    public static boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }

        return Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);
    }
}
