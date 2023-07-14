package fly.web.service;

import fly.web.FlyWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class UserPostServiceTest {

    @Resource
    private UserCollectionService userPostService;


    @Test
    public void removeOrAddTest()
    {

    }
}
