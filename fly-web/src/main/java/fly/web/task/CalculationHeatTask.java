package fly.web.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.web.dao.PostMapper;
import fly.web.entity.model.Post;
import fly.web.entity.from.PostFilterCondition;
import fly.web.entity.vo.PostVO;
import fly.web.service.PostService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class CalculationHeatTask {

    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;

    @Scheduled(cron = "0 0/15 * * * ?")
    public void execute()
    {
        int page = 1;
        int pageSize = 100;
        do {
            IPage<PostVO> posts = getPosts(page, pageSize);

            if(posts == null || posts.getPages() < page){
                System.out.println("process complete");
                break;
            }

            for (PostVO postVO : posts.getRecords()) {
                Post post = postMapper.selectById(postVO.getId());
                double head = postService.calculationHeat(post);
                post.setHeat(head);
                postService.updateHeat(post);
                System.out.println("set post "+post.getId()+" heat");
            }
            page++;
        }while (true);
        System.out.println("task run complete");
    }

    public IPage<PostVO> getPosts(int page, int pageSize) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setStatus(1);

        Page<Post> p = new Page<>();
        p.setCurrent(page).setSize(pageSize);
        return  postService.getByCondition(p, condition);
    }
}
