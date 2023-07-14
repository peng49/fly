package fly.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.FlyWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class PostCommentServiceTest {
    @Resource
    private PostCommentService postCommentService;

    @Test
    public void serviceTest()
    {
        postCommentService.getByUserId(new Page<>(1,10),1L);
    }
}
