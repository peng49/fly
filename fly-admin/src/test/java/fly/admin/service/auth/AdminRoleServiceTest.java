package fly.admin.service.auth;

import fly.admin.FlyAdminApplication;
import fly.admin.repository.AdminRoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyAdminApplication.class})
public class AdminRoleServiceTest {

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Test
    public void roleTest(){
        System.out.println(1);
    }
}
