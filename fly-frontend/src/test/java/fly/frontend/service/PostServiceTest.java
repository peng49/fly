package fly.frontend.service;

import fly.frontend.SpringMainApplication;
import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostAdd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringMainApplication.class})
public class PostServiceTest {
    @Resource
    private PostService postService;

    @Test
    public void findAllTest()
    {
        System.out.println(postService.findByColumnId(1));
    }

    @Test
    public void createTest()
    {
        Post post = new Post();
        post.setTitle("测试标题");
        post.setContent("测试内容");
//        postService.create(post);
    }

    @Test
    public void getCommentsTest()
    {
        System.out.println(postService.getComments(1));
    }

    @Test
    public void timestampTest()
    {
        PostAdd postAdd = new PostAdd();
        postAdd.setColumnId(1);
        postAdd.setTitle("test");
        postAdd.setContent("test content");
        User user = new User();
        user.setId(1);
        postService.create(postAdd,user);
    }
}
