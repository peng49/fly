package fly.web.controller;

import fly.web.entity.vo.ResultVO;
import fly.web.service.UserCollectionService;

import fly.web.service.UserService;
import fly.web.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userCollection")
public class UserCollectionController {
    private final UserCollectionService userCollectionService;

    private final UserService userService;

    public UserCollectionController(UserCollectionService userCollectionService,UserService userService) {
        this.userCollectionService = userCollectionService;
        this.userService = userService;
    }

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("postId") Long postId) {
        userCollectionService.removeOrAdd(userService.getByUsername(HttpUtils.getCurrentUser().getUsername()), postId);
        return ResultVO.builder().code("success").message("Success").build();
    }
}
