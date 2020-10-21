package fly.admin.rest;

import fly.admin.entity.model.FriendLink;
import fly.admin.entity.model.Post;
import fly.admin.entity.request.EditFriendLinkRequest;
import fly.admin.entity.request.EditPostRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "友链管理")
@RestController
@RequestMapping("/api/friend-links")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

    @ApiOperation(value = "新增友链")
    @PostMapping
    public Object add(@RequestBody EditFriendLinkRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        friendLinkService.add(FriendLink.builder()
                                .name(request.getName())
                                .url(request.getUrl())
                                .status(request.getStatus())
                                .build())
                ).build();
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        friendLinkService.delete(friendLinkService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "修改文章")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditFriendLinkRequest request) {
        FriendLink link = friendLinkService.get(id);
        link.setName(request.getName());
        link.setUrl(request.getUrl());
        link.setStatus(request.getStatus());
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(friendLinkService.update(link))
                .build();
    }

    @ApiOperation(value = "获取文章信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(friendLinkService.get(id))
                .build();
    }

    @ApiOperation(value = "查询文章")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);
        return friendLinkService.search(page, pageSize, query);
    }

}
