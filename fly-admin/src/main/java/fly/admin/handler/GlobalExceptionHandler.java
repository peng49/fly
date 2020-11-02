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
        return ResultVO.builder().code("exception").message(ex.getMessage()).build();
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseBody
    public final ResultVO handleAccessExceptions(Exception ex,WebRequest request){
        return ResultVO.builder().code("invalid.token").message(ex.getMessage()).build();
    }
}
