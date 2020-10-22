package fly.admin.rest;

import fly.admin.entity.model.Post;
import fly.admin.entity.request.EditPostRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "文章管理")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Resource
    private PostService postService;

    @ApiOperation(value = "新增文章")
    @PostMapping
    public Object add(@RequestBody EditPostRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        postService.add(Post.builder()
                                .authorId(request.getAuthorId())
                                .columnId(request.getColumnId())
                                .title(request.getTitle())
                                .content(request.getContent())
                                .status(request.getStatus())
                                .build())
                ).build();
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        postService.delete(postService.findOne(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "修改文章")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditPostRequest request) {
        Post post = postService.findOne(id);

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postService.update(post))
                .build();
    }

    @ApiOperation(value = "获取文章信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(postService.get(id))
                .build();
    }

    @ApiOperation(value = "查询文章")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "columnId", required = false) Integer columnId) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);
        query.put("columnId",columnId);
        return postService.search(page, pageSize, query);
    }
}
