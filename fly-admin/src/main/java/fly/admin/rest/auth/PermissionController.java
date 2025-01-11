package fly.admin.rest.auth;

import fly.admin.entity.model.AdminPermission;
import fly.admin.entity.model.AdminRole;
import fly.admin.entity.request.EditAdminPermissionRequest;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminPermissionService;
import fly.admin.service.auth.AdminRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@Tag(name = "权限管理")
@RestController("AdminPermissionController")
@RequestMapping("/api/auth/permissions")
public class PermissionController {
    @Resource
    private AdminPermissionService adminPermissionService;

    @Operation(summary = "新增权限")
    @PostMapping
    public Object add(@RequestBody EditAdminPermissionRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminPermissionService.add(AdminPermission.builder()
                                .parentId(request.getParentId())
                                .name(request.getName())
                                .slug(request.getSlug())
                                .httpMethod(request.getHttpMethod())
                                .httpPath(request.getHttpPath())
                                .build())
                ).build();
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminPermissionService.delete(adminPermissionService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新权限")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminPermissionRequest request) {
        AdminPermission permission = adminPermissionService.get(id);
        permission.setName(request.getName());
        permission.setSlug(request.getSlug());
        permission.setHttpPath(request.getHttpPath());
        permission.setHttpMethod(request.getHttpMethod());
        permission.setParentId(request.getParentId());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminPermissionService.update(permission))
                .build();
    }

    @Operation(summary = "获取权限信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminPermissionService.get(id))
                .build();
    }

    @Operation(summary = "查询权限")
    @GetMapping
    public Object search()
    {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminPermissionService.search())
                .build();
    }
}
