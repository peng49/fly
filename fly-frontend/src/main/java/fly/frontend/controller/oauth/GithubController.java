package fly.frontend.controller.oauth;

import fly.frontend.entity.po.OauthAccount;
import fly.frontend.entity.po.User;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/oauth/github")
public class GithubController {
    @Value("${oauth.github.client-id}")
    private String clientId;

    @Value("${oauth.github.client-secret}")
    private String clientSecret;

    @Value("${oauth.github.redirect-uri}")
    private String redirectUri;

    @Resource(name = "GithubOauthServiceImpl")
    private OauthService oauthService;

    @Resource
    private UserService userService;

    @GetMapping("/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        System.out.println("123123");
        String url = String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=user", clientId, redirectUri);
        response.sendRedirect(url);
    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpSession httpSession, HttpServletResponse response) throws IOException {
        OauthAccount account = oauthService.get(code);
        User user = userService.getById(account.getUser().getId());
        httpSession.setAttribute(UserService.LOGIN_KEY, user);

        response.sendRedirect("/user/center");
    }
}
