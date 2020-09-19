package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class PostAutoDraftServiceTest {
    @Resource
    private PostAutoDraftService postAutoDraftService;

    @Test
    public void addTest(){
        PostAutoDraft draft = new PostAutoDraft();
        User user = new User();
        user.setId(1);
        draft.setUser(user);
//        Post post = new Post();
//        draft.setPost(post);

        System.out.println(draft);

        PostAutoDraft newDraft = postAutoDraftService.add(draft);
        System.out.println(newDraft);
    }
}
