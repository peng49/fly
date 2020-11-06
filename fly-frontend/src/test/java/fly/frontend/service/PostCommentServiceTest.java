package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.model.User;
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
    public void serviceTest()
    {

    }
}
