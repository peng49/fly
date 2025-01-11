package fly.web.controller;

import fly.web.entity.model.UserColumn;
import fly.web.entity.vo.UserVO;
import fly.web.service.UserColumnService;
import fly.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user-column")
public class UserColumnController {

    @Resource
    protected UserColumnService userColumnService;

    @GetMapping("/search")
    public Object search() {
        UserVO user = HttpUtils.getCurrentUser();
        List<UserColumn> columns = userColumnService.lambdaQuery().eq(UserColumn::getUserId, user.getId()).list();

        return HttpUtils.success(columns);
    }
}
