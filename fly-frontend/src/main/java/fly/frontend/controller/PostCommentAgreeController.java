package fly.frontend.controller;

import fly.frontend.entity.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postCommentAgree")
public class PostCommentAgreeController {

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd()
    {
        return ResultVO.builder().build();
    }
}
