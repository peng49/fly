package fly.frontend.service;

import fly.frontend.FrontendSpringBootApplication;
import fly.frontend.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FrontendSpringBootApplication.class})
public class PostCommentServiceTest {
    @Resource
    private PostCommentService postCommentService;

    @Test
    public void getByUserId()
    {
        System.out.println(postCommentService.getByUserId(1));
    }
}
