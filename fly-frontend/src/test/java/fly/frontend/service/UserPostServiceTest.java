package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class UserPostServiceTest {

    @Resource
    private UserPostService userPostService;

    @Test
    public void createTest()
    {
        User user = new User();
        user.setId(1);

        System.out.println(userPostService.create(user,14));
    }

    @Test
    public void getTest(){
        System.out.println(userPostService.isExisted(1,1));
    }
}
