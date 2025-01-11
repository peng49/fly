package fly.admin.rest;

import fly.admin.entity.vo.ResultVO;
import fly.admin.service.OauthAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.HashMap;

@Tag(name = "第三方账号管理")
@RestController
@RequestMapping("/api/oauth-accounts")
public class OauthAccountController {

    @Resource
    private OauthAccountService oauthAccountService;

    @Operation(summary = "账号查询")
    @GetMapping
    public ResultVO search(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "keyword", required = false) String keyword) {
        HashMap<String, Object> query = new HashMap<>();
        query.put("keyword", keyword);

        return oauthAccountService.search(page, pageSize, query);
    }
}
