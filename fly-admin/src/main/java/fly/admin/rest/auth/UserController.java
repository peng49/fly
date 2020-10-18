package fly.admin.rest.auth;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.request.EditAdminUserRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户管理")
@RestController("AdminUserController")
@RequestMapping("/api/auth/users")
public class UserController {

    @Resource
    private AdminUserService adminUserService;

    @ApiOperation(value = "新增用户")
    @PostMapping
    public Object add(@RequestBody EditAdminUserRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminUserService.add(AdminUser.builder()
                                .avatar(request.getAvatar())
                                .username(request.getUsername())
                                .password(request.getPassword())
                                .name(request.getName())
                                .build())
                ).build();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminUserService.delete(adminUserService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminUserRequest request) {
        AdminUser user = adminUserService.get(id);
        user.setAvatar(request.getAvatar());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setName(request.getName());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.update(user))
                .build();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.get(id))
                .build();
    }

    @ApiOperation(value = "查询用户")
    @GetMapping
    public Object search()
    {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.search())
                .build();
    }
}
