package fly.admin.rest;

import fly.admin.entity.vo.ResultVO;
import fly.admin.service.PostCommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.HashMap;

@Tag(name = "评论管理")
@RestController
@RequestMapping("/api/post-comments")
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    @Operation(summary = "删除评论")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id) {
        postCommentService.delete(postCommentService.findOne(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }


    @Operation(summary = "获取单个评论")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") Long id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postCommentService.get(id))
                .build();
    }

    @Operation(summary = "查询评论")
    @GetMapping
    public Object search(
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
            @RequestParam(name = "userId", required = false) Integer userId) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("userId",userId);

        return postCommentService.search(page,pageSize,query);
    }

}
