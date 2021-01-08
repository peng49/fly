package fly.frontend.controller;

import fly.frontend.entity.vo.ResultVO;
import fly.frontend.service.UserBlacklistService;
import fly.frontend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userBlacklist")
public class UserBlacklistController {
    @Resource
    private UserBlacklistService userBlacklistService;

    @Resource
    private UserService userService;

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("userId") Long userId) {
        userBlacklistService.removeOrAdd(userService.getById(userId));
        return ResultVO.builder().code("success").message("Success").build();
    }
}
