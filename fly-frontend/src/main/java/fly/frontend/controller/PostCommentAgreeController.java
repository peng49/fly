package fly.frontend.controller;

import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.ResultVO;
import fly.frontend.service.PostCommentAgreeService;
import fly.frontend.service.PostCommentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/postCommentAgree")
public class PostCommentAgreeController {

    @Resource
    private PostCommentService postCommentService;

    @Resource
    private PostCommentAgreeService postCommentAgreeService;

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("commentId") Long commentId) {
        PostComment postComment = postCommentService.getById(commentId);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        postCommentAgreeService.removeOrAdd(postComment, user);
        return ResultVO.builder().code("success").message("Success").build();
    }
}
