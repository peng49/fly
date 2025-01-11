package fly.admin.rest;

import fly.admin.entity.model.Column;
import fly.admin.entity.request.EditColumnRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.ColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.HashMap;

@Tag(name = "栏目管理")
@RestController
@RequestMapping("/api/columns")
public class ColumnController {

    @Resource
    private ColumnService columnService;

    @Operation(summary = "新增栏目")
    @PostMapping
    public Object add(@RequestBody EditColumnRequest request) {
        return ResultVO.builder().code("success")
                .message("Success")
                .data(columnService.add(
                        Column.builder()
                                .name(request.getName())
                                .sort(request.getSort())
                                .build())
                )
                .build();
    }

    @Operation(summary = "删除栏目")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        columnService.delete(columnService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新栏目")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditColumnRequest request) {
        Column column = columnService.get(id);
        column.setName(request.getName());
        column.setSort(request.getSort());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(columnService.update(column))
                .build();
    }

    @Operation(summary = "获取栏目", description = "获取指定栏目信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(columnService.get(id))
                .build();
    }

    @Operation(summary = "查询栏目")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);

        return columnService.search(page, pageSize, query);
    }
}
