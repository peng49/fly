package fly.frontend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.FlyFrontendApplication;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class PostServiceTest {
    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;

    @Test
    public void serviceTest()
    {
    }

}
