package fly.admin.rest.auth;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.request.EditAdminUserRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@Tag(name = "后台用户管理")
@RestController("AdminUserController")
@RequestMapping("/api/auth/users")
public class UserController {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Object login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.login(username, password))
                .build();
    }


    @Operation(summary = "新增用户")
    @PostMapping
    public Object add(@RequestBody EditAdminUserRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminUserService.add(AdminUser.builder()
                                .avatar(request.getAvatar())
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .name(request.getName())
                                .build(),request)
                ).build();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminUserService.delete(adminUserService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @Operation(summary = "更新用户")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminUserRequest request) {
        AdminUser user = adminUserService.get(id);
        user.setAvatar(request.getAvatar());
        user.setUsername(request.getUsername());
        user.setName(request.getName());

        if (request.getPassword() != null
                && !request.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.update(user,request))
                .build();
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.get(id))
                .build();
    }

    @Operation(summary = "查询用户")
    @GetMapping
    public Object search() {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminUserService.search())
                .build();
    }
}
