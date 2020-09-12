package fly.frontend.controller.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/oauth/github")
public class GithubController {
    @GetMapping("/redirect")
    public void redirect() {

    }

    @GetMapping("/callback")
    public void callback(@RequestParam("code") String code) {

    }
}
