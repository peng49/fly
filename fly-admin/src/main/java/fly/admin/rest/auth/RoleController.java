package fly.admin.rest.auth;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@Tag(name = "角色管理")
@RestController("AdminRoleController")
@RequestMapping("/api/auth/roles")
public class RoleController {
    @Resource
    private AdminRoleService adminRoleService;

    @Operation(summary = "新增角色")
    @PostMapping
    public Object add(@RequestBody EditAdminRoleRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminRoleService.add(request)
                ).build();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminRoleService.delete(id);
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新角色")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminRoleRequest request) {
        AdminRole role = adminRoleService.findOne(id);
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.update(role,request))
                .build();
    }

    @Operation(summary = "获取角色信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.get(id))
                .build();
    }

    @Operation(summary = "查询角色")
    @GetMapping
    public Object search() {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.search())
                .build();
    }
}
