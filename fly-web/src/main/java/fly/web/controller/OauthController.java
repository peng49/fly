package fly.web.controller;

import fly.web.entity.model.OauthAccount;
import fly.web.entity.model.User;
import fly.web.service.OauthService;
import fly.web.service.UserService;
import fly.web.shiro.OauthToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController {

    @Resource(name = "GiteeOauthServiceImpl")
    private OauthService giteeOauthService;

    @Resource(name = "GithubOauthServiceImpl")
    private OauthService githubOauthService;

    @Resource
    private UserService userService;

    @GetMapping("/{platform}/redirect")
    public void redirect(HttpServletResponse response,
                         @PathVariable("platform") String platform) throws IOException, ModelAndViewDefiningException {
        response.sendRedirect(getOauthService(platform).getRedirectUrl());
    }

    @GetMapping("/{platform}/callback")
    public void callback(@RequestParam("code") String code,
                         @PathVariable("platform") String platform,
                         HttpServletResponse response) throws IOException, ModelAndViewDefiningException {
        log.info(platform+" code:"+code);

        OauthAccount account = getOauthService(platform).get(code);
        User user = userService.getById(account.getUserId());

        OauthToken oauthToken = new OauthToken(user);

        Subject subject = SecurityUtils.getSubject();

        subject.login(oauthToken);

        response.sendRedirect("/user/center");
    }

    private OauthService getOauthService(String platform) throws ModelAndViewDefiningException {
        switch (platform) {
            case "gitee":
                return giteeOauthService;
            case "github":
                return githubOauthService;
            default:
                ModelAndView mv = new ModelAndView("page/404");
                mv.setStatus(HttpStatus.NOT_FOUND);
                throw new ModelAndViewDefiningException(mv);
        }
    }
}
