package fly.admin.rest.auth;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "角色管理")
@RestController("AdminRoleController")
@RequestMapping("/api/auth/roles")
public class RoleController {
    @Resource
    private AdminRoleService adminRoleService;

    @ApiOperation(value = "新增角色")
    @PostMapping
    public Object add(@RequestBody EditAdminRoleRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminRoleService.add(request)
                ).build();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminRoleService.delete(id);
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminRoleRequest request) {
        AdminRole role = adminRoleService.findOne(id);
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.update(role,request))
                .build();
    }

    @ApiOperation(value = "获取角色信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.get(id))
                .build();
    }

    @ApiOperation(value = "查询角色")
    @GetMapping
    public Object search() {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminRoleService.search())
                .build();
    }
}
