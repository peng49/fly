package fly.admin.rest;

import fly.admin.entity.model.Navigation;
import fly.admin.entity.request.EditNavigationRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.NavigationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.HashMap;

@Tag(name = "导航管理")
@RestController
@RequestMapping("/api/navigations")
public class NavigationController {
    @Resource
    private NavigationService navigationService;

    @Operation(summary = "新增导航")
    @PostMapping
    public Object add(@RequestBody EditNavigationRequest request) {
        return ResultVO.builder().code("success")
                .message("Success")
                .data(navigationService.add(
                        Navigation.builder()
                                .title(request.getTitle())
                                .url(request.getUrl())
                                .sort(request.getSort())
                                .status(request.getStatus())
                                .parentId(request.getParentId())
                                .build())
                )
                .build();
    }

    @Operation(summary = "删除导航")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        navigationService.delete(navigationService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新导航")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditNavigationRequest request) {
        Navigation navigation = navigationService.get(id);
        navigation.setTitle(request.getTitle());
        navigation.setUrl(request.getUrl());
        navigation.setParentId(request.getParentId());
        navigation.setSort(request.getSort());
        navigation.setStatus(request.getStatus());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(navigationService.update(navigation))
                .build();
    }

    @Operation(summary = "获取导航", description = "获取指定导航信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(navigationService.get(id))
                .build();
    }

    @Operation(summary = "查询导航")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);

        return navigationService.search(page, pageSize, query);
    }
}
