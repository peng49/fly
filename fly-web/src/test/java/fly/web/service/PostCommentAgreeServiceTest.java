package fly.web.service;

import fly.web.FlyWebApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class PostCommentAgreeServiceTest {
    @Autowired
    private PostCommentAgreeService postCommentAgreeService;

}
