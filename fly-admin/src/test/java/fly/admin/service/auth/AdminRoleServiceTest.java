package fly.admin.service.auth;

import fly.admin.FlyAdminApplication;
import fly.admin.entity.model.*;
import fly.admin.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyAdminApplication.class})
@Slf4j
public class AdminRoleServiceTest {

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Resource
    private AdminUserRepository adminUserRepository;

    @Resource
    private AdminUserRoleRepository adminUserRoleRepository;

    @Resource
    private AdminRolePermissionRepository adminRolePermissionRepository;

    @Resource
    private AdminPermissionRepository adminPermissionRepository;


    @Resource
    private AdminUserService adminUserService;

    @Test
    public void roleTest(){
        AdminUser user = adminUserService.get(1);

        List<AdminUserRole> items = adminUserRoleRepository.findByUserId(user.getId());

        List<AdminRolePermission> permissionList = adminRolePermissionRepository.findByRoleIdIn(items.stream().map(AdminUserRole::getRoleId).collect(Collectors.toList()));

        List<Integer> permissionIds = permissionList.stream().map(AdminRolePermission::getPermissionId).collect(Collectors.toList());

        List<AdminPermission> permissions = adminPermissionRepository.findByIdIn(permissionIds);

        Map<String, Set<String>> map = new HashMap<>();
        permissions.forEach(permission -> {
            String[] methods = permission.getHttpMethod().split(",");
            for (String method : methods) {
                String[] uris = permission.getHttpPath().split("\n");
                for (String uri : uris) {
                    map.computeIfAbsent(method, k -> new HashSet<>()).add(uri);
                }
            }
        });

        String url = "/users";
        String method = "GET";

        Set<String> uris = map.get(method);
        AtomicBoolean has = new AtomicBoolean(false);
        uris.forEach(uri -> {
            boolean matches = FileSystems.getDefault()
                    .getPathMatcher("glob:" + uri)
                    .matches(Paths.get(url));

            log.info(String.valueOf(matches));
            log.info(uri);

//            boolean matches = Pattern.matches(uri, url);
//
//            log.info(String.valueOf(matches));

            if(matches){
                has.set(true);
            }
        });

        log.info(String.valueOf(has.get()));

        log.info(map.toString());
    }
}
