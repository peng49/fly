package fly.admin.rest.auth;

import fly.admin.entity.model.AdminMenu;
import fly.admin.entity.request.EditAdminMenuRequest;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.auth.AdminMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "菜单管理")
@RestController("AdminMenuController")
@RequestMapping("/api/auth/menus")
public class MenuController {

    @Resource
    private AdminMenuService adminMenuService;

    @ApiOperation(value = "新增菜单")
    @PostMapping
    public Object add(@RequestBody EditAdminMenuRequest request) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        adminMenuService.add(AdminMenu.builder()
                                .icon(request.getIcon())
                                .sort(request.getSort())
                                .parentId(request.getParentId())
                                .uri(request.getUri())
                                .component(request.getComponent())
                                .title(request.getTitle())
                                .permission(request.getPermission())
                                .build())
                ).build();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        adminMenuService.delete(adminMenuService.get(id));
        return ResultVO.builder()
                .code("success")
                .message("删除成功")
                .build();
    }

    @ApiOperation(value = "更新菜单")
    @PutMapping("/{id}")
    public Object update(@PathVariable("id") int id, @RequestBody EditAdminMenuRequest request) {
        AdminMenu menu = adminMenuService.get(id);
        menu.setIcon(request.getIcon());
        menu.setSort(request.getSort());
        menu.setParentId(request.getParentId());
        menu.setPermission(request.getPermission());
        menu.setTitle(request.getTitle());
        menu.setUri(request.getUri());
        menu.setComponent(request.getComponent());

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminMenuService.update(menu))
                .build();
    }

    @ApiOperation(value = "获取单个菜单", notes = "获取单个菜单信息")
    @GetMapping("/{id}")
    public Object get(@PathVariable("id") int id) {
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(adminMenuService.get(id))
                .build();
    }

    @ApiOperation(value = "查询菜单")
    @GetMapping
    public Object search()
    {
        return ResultVO.builder()
                .code("20000")
                .message("Success")
                .data(adminMenuService.findAll())
                .build();
    }
}
