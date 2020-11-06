package fly.frontend.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
@Slf4j
public class PostAutoDraftServiceTest {
    @Resource
    private PostAutoDraftService postAutoDraftService;

    @Test
    public void serviceTest()
    {
        PostAutoDraft draft = postAutoDraftService.getForPost(Post.builder().id(1L).authorId(1L).build());
//        PostAutoDraft draft = postAutoDraftService.getOne(Wrappers.lambdaQuery(PostAutoDraft.class).eq(PostAutoDraft::getUserId, 1L));
        log.info(draft.toString());
    }
}
