package fly.admin.rest;

import fly.admin.entity.model.Column;
import fly.admin.entity.request.EditColumnRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.ColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "栏目管理")
@RestController
@RequestMapping("/api/columns")
public class ColumnController {

    @Resource
    private ColumnService columnService;

    @ApiOperation(value = "新增栏目")
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

    @ApiOperation(value = "删除栏目")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id)
    {
        columnService.delete(columnService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新栏目")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id,@RequestBody EditColumnRequest request) {
        Column column = columnService.get(id);
        column.setName(request.getName());
        column.setSort(request.getSort());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(columnService.update(column))
                .build();
    }

    @ApiOperation(value = "获取栏目",notes = "获取指定栏目信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(columnService.get(id))
                .build();
    }

    @ApiOperation(value = "查询栏目")
    @GetMapping
    public Object search()
    {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(columnService.search())
                .build();
    }
}
