package fly.frontend.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final Map<String,Object> handleAllExceptions(Exception ex, WebRequest request) throws ModelAndViewDefiningException {
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", "exception");
            map.put("name", ex.getClass().getName());
            map.put("message", ex.getLocalizedMessage());
            map.put("trace",ex.getStackTrace());

            log.error(Arrays.toString(ex.getStackTrace()));
            return map;
        } else {
            ModelAndView mv = new ModelAndView("page/tips");
            mv.addObject("message",ex.getLocalizedMessage());
            throw new ModelAndViewDefiningException(mv);
        }

    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ModelAndView handleResponseStatusException(ResponseStatusException ex, WebRequest request) throws ModelAndViewDefiningException {
        if (HttpStatus.NOT_FOUND.equals(ex.getStatus())) {
            ModelAndView mv = new ModelAndView("page/404");
            log.info("404 page");
            mv.setStatus(HttpStatus.NOT_FOUND);
//            throw new ModelAndViewDefiningException(mv);
            return mv;
        }
        return null;
    }


    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public final Map<String, Object> handleValidateExceptions(Exception ex, WebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "validate.error");

        String msg = null;
        /// BindException
        if (ex instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) ex).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
        } else if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// ValidationException 的子类异常ConstraintViolationException
        } else if (ex instanceof ConstraintViolationException) {
            /*
             * ConstraintViolationException的e.getMessage()形如
             *     {方法名}.{参数名}: {message}
             *  这里只需要取后面的message即可
             */
            msg = ex.getMessage();
            if (msg != null) {
                int lastIndex = msg.lastIndexOf(':');
                if (lastIndex >= 0) {
                    msg = msg.substring(lastIndex + 1).trim();
                }
            }
            /// ValidationException 的其它子类异常
        } else {
            msg = "处理参数时异常";
        }

        map.put("name", ex.getClass().getName());
        map.put("message", msg);
        return map;
    }
}
