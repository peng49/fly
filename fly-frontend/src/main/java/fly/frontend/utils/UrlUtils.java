package fly.frontend.utils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtils {
    public static String setParameter(HttpServletRequest request,String name,String value){

        String path = "";
        if(request.getParameter(name) == null){
            path = request.getQueryString() == null ? name+"="+value : request.getQueryString()+"&"+name+"="+value;
        }else{
            String queryString = request.getQueryString();
            path = queryString.replaceAll(name+"=.*?(&|$)",name+"="+value+"$1");
        }
        return path;
    }
}
