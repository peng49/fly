package fly.web.controller;

import fly.web.entity.vo.ResultVO;
import fly.web.service.UserFollowService;
import fly.web.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

public class UserFollowController {
    @Resource
    private UserFollowService userFollowService;

    @Resource
    private UserService userService;

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("userId") Long userId) {
        userFollowService.removeOrAdd(userService.getById(userId));
        return ResultVO.builder().code("success").message("Success").build();
    }
}
