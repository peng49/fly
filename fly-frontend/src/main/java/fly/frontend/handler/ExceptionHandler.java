package fly.frontend.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public final Map handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "exception");
        map.put("message", ex.getLocalizedMessage());
        return map;
    }
}
