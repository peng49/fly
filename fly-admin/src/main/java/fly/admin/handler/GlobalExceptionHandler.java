package fly.admin.handler;

import fly.admin.entity.vo.ResultVO;
import fly.admin.exception.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResultVO handleAllExceptions(Exception ex, WebRequest request) {
        String code = "exception";
        if (ex instanceof InvalidTokenException) {
            code = "invalid.token";
        }
        return ResultVO.builder().code(code).message(ex.getMessage()).build();
    }
}
