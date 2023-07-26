package fly.admin.handler;

import fly.admin.entity.vo.ResultVO;
import fly.admin.util.Tools;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final ResultVO handleAllExceptions(Exception ex, WebRequest request) {
        return ResultVO.builder().code(Tools.getCode(ex)).message(ex.getMessage()).build();
    }
}
