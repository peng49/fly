package fly.frontend.controller;

import fly.frontend.entity.from.EditUserCategoryFrom;
import fly.frontend.entity.model.UserCategory;
import fly.frontend.entity.vo.ResultVO;
import fly.frontend.service.UserCategoryService;
import fly.frontend.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@ResponseBody
@RequestMapping("/userCategory")
public class UserCategoryController {

    @Resource
    private UserCategoryService userCategoryService;

    @GetMapping("/get")
    private ResultVO get() {
        return ResultVO.builder().code("success").message("Success").data(
                userCategoryService.lambdaQuery()
                        .eq(UserCategory::getUserId, HttpUtils.getCurrentUser().getId())
                        .eq(UserCategory::getEnable, 1)
                        .list()
        ).build();
    }

    @PostMapping("/createOrEdit")
    public ResultVO createOrEdit(@RequestBody EditUserCategoryFrom from) {
        UserCategory category;
        if (from.getId() != null) {
            category = userCategoryService.getById(from.getId());
            category.setName(from.getName());
            category.setUpdatedAt(LocalDateTime.now());
            userCategoryService.updateById(category);
        } else {
            category = new UserCategory();
            category.setName(from.getName());

            category.setUserId(HttpUtils.getCurrentUser().getId());
            category.setCreatedAt(LocalDateTime.now());
            userCategoryService.save(category);
        }
        return ResultVO.builder().code("success").message("Success").data(category).build();
    }
}
