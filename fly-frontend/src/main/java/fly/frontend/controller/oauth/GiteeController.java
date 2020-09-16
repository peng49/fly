package fly.frontend.controller.oauth;

import fly.frontend.entity.OauthAccount;
import fly.frontend.entity.User;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/oauth/gitee")
public class GiteeController {

    @Resource(name="GiteeOauthServiceImpl")
    private OauthService oauthService;

    @Resource
    private UserService userService;

    @GetMapping("/redirect")
    public void redirect() {

    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpServletResponse response, HttpSession httpSession) throws IOException {
        OauthAccount account = oauthService.get(code);
        User user = userService.getById(account.getUser().getId());

        httpSession.setAttribute(UserService.LOGIN_KEY,user);
        response.sendRedirect("/user/center");
    }
}
