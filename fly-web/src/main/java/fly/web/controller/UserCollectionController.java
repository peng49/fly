package fly.web.controller;

import fly.web.entity.model.User;
import fly.web.entity.vo.ResultVO;
import fly.web.service.UserCollectionService;
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
