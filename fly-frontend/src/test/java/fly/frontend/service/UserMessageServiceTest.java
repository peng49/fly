package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class UserMessageServiceTest {
    @Resource
    private UserMessageService userMessageService;


    @Test
    public void getMessagesForUserTest(){
        User user = new User();
        user.setId(1);
        System.out.println(userMessageService.getMessagesForUser(user));
    }

    @Test
    public void deleteTest()
    {
        User user = new User();
        user.setId(1);
        userMessageService.deleteByUser(user);
    }
}
