package fly.frontend.controller.oauth;

import fly.frontend.service.OauthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/oauth/gitee")
public class GiteeController {

    @Resource
    private OauthService oauthService;

    @GetMapping("/redirect")
    public void redirect() {

    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        int len = code.length();
        System.out.println(len);
        System.out.println(oauthService.get(code));
    }
}
