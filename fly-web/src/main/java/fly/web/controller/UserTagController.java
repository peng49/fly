package fly.web.controller;

import fly.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-tag")
public class UserTagController {

    @GetMapping("/search")
    public Object search() {
        return HttpUtils.success();
    }
}
