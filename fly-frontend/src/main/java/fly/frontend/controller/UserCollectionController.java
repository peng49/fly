package fly.frontend.controller;

import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.ResultVO;
import fly.frontend.service.UserCollectionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userCollection")
public class UserCollectionController {

    @Resource
    private UserCollectionService userCollectionService;

    @PostMapping("/removeOrAdd")
    @ResponseBody
    public ResultVO removeOrAdd(@RequestParam("postId") Long postId) {
        userCollectionService.removeOrAdd((User) SecurityUtils.getSubject().getPrincipal(), postId);
        return ResultVO.builder().code("success").message("Success").build();
    }
}
