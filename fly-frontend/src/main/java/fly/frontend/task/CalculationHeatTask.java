package fly.frontend.task;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fly.frontend.entity.Post;
import fly.frontend.pojo.PostFilterCondition;
import fly.frontend.service.PostService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class CalculationHeatTask {

    @Resource
    private PostService postService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void execute()
    {
        int page = 1;
        int pageSize = 100;
        do {
            Page<Post> posts = getPosts(page, pageSize);
            if(posts == null || posts.getPageNum() < page){
                System.out.println("process complete");
                break;
            }

            for (Post post : posts) {
                double head = postService.calculationHeat(post);
                post.setHeat(head);
                postService.updateHeat(post);
                System.out.println("set post "+post.getId()+" heat");
            }
            page++;
        }while (true);
        System.out.println("task run complete");
    }

    public Page<Post> getPosts(int page, int pageSize)
    {
        PageHelper.startPage(page,pageSize);
        PostFilterCondition condition = new PostFilterCondition();
        condition.setStatus(1);
        return (Page<Post>)postService.getByCondition(condition);
    }
}
