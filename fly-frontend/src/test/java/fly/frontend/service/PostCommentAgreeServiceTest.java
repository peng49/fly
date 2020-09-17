package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.po.PostComment;
import fly.frontend.entity.po.PostCommentAgree;
import fly.frontend.entity.po.User;
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
        User user = new User();
        user.setId(1);
        PostComment comment = new PostComment();
        comment.setId(2);
        PostCommentAgree postCommentAgree = new PostCommentAgree();
        postCommentAgree.setUser(user);
        postCommentAgree.setPostComment(comment);
        System.out.println(postCommentAgreeService.create(postCommentAgree));
    }

    @Test
    public void isExistedTest(){
        User user = new User();
        user.setId(1);
        System.out.println(postCommentAgreeService.isExisted(user,2));
    }

    @Test
    public void deleteTest(){
        User user = new User();
        user.setId(1);
        postCommentAgreeService.delete(user,2);
    }
}
