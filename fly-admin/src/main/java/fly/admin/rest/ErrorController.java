package fly.admin.rest;

import fly.admin.exception.InvalidTokenException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ErrorController extends BasicErrorController {
    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> attributes = getErrorAttributes(request, isIncludeStackTrace(request,MediaType.ALL));
        Map<String, Object> attributes = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        if (InvalidTokenException.class.getName().equals(attributes.get("exception"))) {

        }
        return super.error(request);
    }
}
