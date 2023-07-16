package fly.admin.service;

import fly.admin.FlyAdminApplication;
import fly.admin.entity.model.SystemConfig;
import fly.admin.repository.SystemConfigRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyAdminApplication.class})
public class SystemConfigServiceTest extends TestCase {
    @Resource
    private SystemConfigRepository systemConfigRepository;

    @Test
    public void testJpaSearch(){
        SystemConfig config = systemConfigRepository.findByAttribute("sitename");
        System.out.println(config);
    }
}
