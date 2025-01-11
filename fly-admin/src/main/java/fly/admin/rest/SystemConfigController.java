package fly.admin.rest;

import fly.admin.entity.model.SystemConfig;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.SystemConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Tag(name = "系统配置")
@RestController
@RequestMapping("/api/system-config")
public class SystemConfigController {
    @Resource
    private SystemConfigService systemConfigService;

    @Operation(summary = "新增配置")
    @PostMapping
    public Object add(@RequestBody SystemConfig config) {
        return ResultVO.builder().code("success")
                .message("Success")
                .data(systemConfigService.add(
                        SystemConfig.builder()
                                .attribute(config.getAttribute())
                                .value(config.getValue())
                                .remark(config.getRemark())
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build())
                )
                .build();
    }

    @Operation(summary = "删除配置")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        systemConfigService.delete(systemConfigService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新配置")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody SystemConfig request) {
        SystemConfig config = systemConfigService.get(id);
        config.setRemark(request.getRemark());
        config.setValue(request.getValue());
        config.setUpdatedAt(LocalDateTime.now());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(systemConfigService.update(config))
                .build();
    }

    @Operation(summary = "获取配置", description = "获取指定配置信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(systemConfigService.get(id))
                .build();
    }

    @Operation(summary = "查询配置")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);

        return systemConfigService.search(page, pageSize, query);
    }
}
