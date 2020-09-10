package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.User;
import fly.frontend.entity.UserMessage;
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
    public void createTest()
    {
        UserMessage userMessage = new UserMessage();
        userMessage.setType("reply");
        userMessage.setContent("回复消息");
        User user = new User();
        user.setId(1);
        userMessage.setReceiver(user);
        userMessage.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        int id = userMessageService.create(userMessage);
        System.out.println(id);
    }

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
