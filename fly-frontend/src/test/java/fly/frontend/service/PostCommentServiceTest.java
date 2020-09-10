package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class PostCommentServiceTest {
    @Resource
    private PostCommentService postCommentService;

    @Test
    public void getByUserId()
    {
        System.out.println(postCommentService.get(1));
    }

    @Test
    public void getUsersByContentTest()
    {
        String content = "@T002 这是什么?@peng49 回复消息";

        List<User> users = postCommentService.getUsersByContent(content);

        System.out.println(users);
    }
}
