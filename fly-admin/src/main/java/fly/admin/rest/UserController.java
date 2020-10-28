package fly.admin.rest;

import fly.admin.entity.model.User;
import fly.admin.entity.request.EditUserRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Resource
    private UserService userService;


    @Resource
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "新增用户")
    @PostMapping
    public Object add(@RequestBody EditUserRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        userService.add(User.builder()
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .avatar(request.getAvatar())
                                .city(request.getCity())
                                .build())
                ).build();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        userService.delete(userService.findOne(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditUserRequest request) {
        User user = userService.findOne(id);
        user.setAvatar(request.getAvatar());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        if(request.getPassword() != null
                && !request.getPassword().equals(user.getPassword())){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(userService.update(user))
                .build();
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(userService.get(id))
                .build();
    }

    @ApiOperation(value = "查询用户")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);
        return userService.search(page, pageSize, query);
    }
}
