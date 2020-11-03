package fly.frontend.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.regex.Pattern;

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

    public static boolean isMobile(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }

        return Pattern.matches(".*(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone).*", userAgent);
    }

    public static Object success() {
        return HttpUtils.response("success", "OK");
    }

    public static Object success(Object data) {
        if (data instanceof IPage) {
            HashMap<Object, Object> hash = new HashMap<>();
            hash.put("total", ((IPage<?>) data).getTotal());
            hash.put("page", ((IPage<?>) data).getPages());
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
}
