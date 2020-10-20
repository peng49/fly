package fly.admin.rest;

import fly.admin.entity.model.Post;
import fly.admin.entity.request.EditPostRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "文章管理")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Resource
    private PostService postService;

    @ApiOperation(value = "新增用户")
    @PostMapping
    public Object add(@RequestBody EditPostRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        postService.add(Post.builder().build())
                ).build();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        postService.delete(postService.findOne(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditPostRequest request) {
        Post post = postService.findOne(id);

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postService.update(post))
                .build();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postService.get(id))
                .build();
    }

    @ApiOperation(value = "查询用户")
    @GetMapping
    public Object search()
    {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postService.search())
                .build();
    }

}
