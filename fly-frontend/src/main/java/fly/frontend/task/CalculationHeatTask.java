package fly.frontend.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.from.PostFilterCondition;
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
            IPage<Post> posts = getPosts(page, pageSize);

            if(posts == null || posts.getPages() < page){
                System.out.println("process complete");
                break;
            }

            for (Post post : posts.getRecords()) {
                double head = postService.calculationHeat(post);
                post.setHeat(head);
                postService.updateHeat(post);
                System.out.println("set post "+post.getId()+" heat");
            }
            page++;
        }while (true);
        System.out.println("task run complete");
    }

    public IPage<Post> getPosts(int page, int pageSize) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setStatus(1);

        Page<Post> p = new Page<>();
        p.setCurrent(page).setSize(pageSize);
        return  postService.getByCondition(p, condition);
    }
}
