package fly.admin.service.auth;

import fly.admin.FlyAdminApplication;
import fly.admin.entity.model.AdminUser;
import fly.admin.entity.request.EditAdminUserRequest;
import fly.admin.entity.vo.AdminUserVO;
import fly.admin.repository.AdminRoleRepository;

import fly.admin.repository.AdminUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    private AdminUserService adminUserService;

    @Test
    public void roleTest(){
        AdminUser admin = adminUserRepository.findByUsername("admin");

        log.info(admin.toString());

//        List<Integer> userIds = users.stream().map(AdminUser::getId).collect(Collectors.toList());

//        log.info(userIds.toString());
    }
}
