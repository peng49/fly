package fly.admin.rest;

import fly.admin.entity.vo.ResultVO;
import fly.admin.service.PostCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "评论管理")
@RestController
@RequestMapping("/api/post-comments")
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    @ApiOperation(value = "删除评论")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        postCommentService.delete(postCommentService.findOne(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }


    @ApiOperation(value = "获取单个评论")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postCommentService.get(id))
                .build();
    }

    @ApiOperation(value = "查询评论")
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
