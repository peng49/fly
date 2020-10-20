package fly.admin.rest;

import fly.admin.service.FriendLinkService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "友链管理")
@RestController
@RequestMapping("/api/friend-links")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

}
