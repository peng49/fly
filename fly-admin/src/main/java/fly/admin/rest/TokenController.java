package fly.admin.rest;

import fly.admin.entity.vo.ResultVO;
import fly.admin.entity.vo.UserLoginVO;
import fly.admin.service.auth.AdminUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "后台")
@RestController
@RequestMapping("/api")
public class TokenController {

    @Resource
    private AdminUserService adminUserService;

    /**
     *  登录获取token
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminUserService.login(username, password)
                ).build();
    }
}
