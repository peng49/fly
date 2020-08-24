package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostEdit;
import fly.frontend.pojo.PostFilterCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
public class PostServiceTest {
    @Resource
    private PostService postService;
    

    @Test
    public void findAllTest()
    {
        System.out.println(postService.findByColumnId(1));
    }


    @Test
    public void getByConditionTest()
    {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setList("essence");
        condition.setAuthorId(1);
        condition.setColumnId(10);

        System.out.println(postService.getByCondition(condition));
    }

    @Test
    public void timestampTest()
    {
        PostEdit postAdd = new PostEdit();
        postAdd.setColumnId(1);
        postAdd.setTitle("test");
        postAdd.setContent("test content");
        User user = new User();
        user.setId(1);
        postService.create(postAdd,user);
    }

    @Test
    public void templateTest()
    {
        String tpl = "m/common/link.ftl";
    }
}
