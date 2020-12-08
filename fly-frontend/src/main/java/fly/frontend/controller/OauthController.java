package fly.frontend.controller;

import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
                         HttpServletResponse response,
                         HttpSession httpSession) throws IOException, ModelAndViewDefiningException {
        OauthAccount account = getOauthService(platform).get(code);
        UserVO user = userService.get(account.getUserId());

        httpSession.setAttribute(UserService.LOGIN_KEY, user);
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
