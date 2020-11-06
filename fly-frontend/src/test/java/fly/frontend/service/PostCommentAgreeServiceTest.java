package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class PostCommentAgreeServiceTest {
    @Autowired
    private PostCommentAgreeService postCommentAgreeService;

    @Test
    public void createTest(){

    }

    @Test
    public void isExistedTest(){
        User user = new User();
        user.setId(1L);
        System.out.println(postCommentAgreeService.isExisted(user,2L));
    }

    @Test
    public void deleteTest(){
        User user = new User();
        user.setId(1L);
        postCommentAgreeService.delete(user,2L);
    }
}
