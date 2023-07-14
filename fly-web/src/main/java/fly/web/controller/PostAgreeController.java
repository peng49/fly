package fly.web.controller;

import fly.web.entity.vo.ResultVO;
import fly.web.entity.vo.UserVO;
import fly.web.service.PostAgreeService;
import fly.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章点赞
 */
@RestController
@RequestMapping("/postAgree")
public class PostAgreeController {
    @Resource
    private PostAgreeService postAgreeService;

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("postId") Long postId) {
        UserVO userVO = HttpUtils.getCurrentUser();
        postAgreeService.removeOrAdd(postId, userVO.getId());
        return ResultVO.builder().code("success").message("操作成功").build();
    }
}
