package fly.web.controller;

import fly.web.entity.model.UserTag;
import fly.web.entity.vo.UserVO;
import fly.web.service.UserTagService;
import fly.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user-tag")
public class UserTagController {

    @Resource
    protected UserTagService userTagService;

    @GetMapping("/search")
    public Object search() {
        UserVO user = HttpUtils.getCurrentUser();
        List<UserTag> tags = userTagService.lambdaQuery().eq(UserTag::getUserId, user.getId()).list();

        return HttpUtils.success(tags);
    }
}
