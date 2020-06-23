package fly.frontend.service;

import fly.frontend.FrontendSpringBootApplication;
import fly.frontend.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FrontendSpringBootApplication.class})
public class PostServiceTest {
    @Resource
    private PostService postService;

    @Test
    public void findAllTest()
    {
        System.out.println(postService.findByAuthorId(1));
    }

    @Test
    public void createTest()
    {
        Post post = new Post();
        post.setTitle("测试标题");
        post.setContent("测试内容");
//        postService.create(post);
    }
}
