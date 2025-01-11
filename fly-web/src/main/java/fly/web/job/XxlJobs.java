package fly.web.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import fly.web.dao.PostMapper;
import fly.web.entity.from.PostFilterCondition;
import fly.web.entity.model.Post;
import fly.web.entity.vo.PostVO;
import fly.web.service.PostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XxlJobs {
    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;


    @XxlJob("updatePostHeat")
    public ReturnT<String> updatePostHeat() {
        int page = 1;
        int pageSize = 100;
        do {
            IPage<PostVO> posts = getPosts(page, pageSize);

            if (posts == null || posts.getPages() < page) {
                XxlJobHelper.log("process complete");
                log.debug("process complete");
                break;
            }

            for (PostVO postVO : posts.getRecords()) {
                Post post = postMapper.selectById(postVO.getId());
                double head = postService.calculationHeat(post);
                post.setHeat(head);
                postService.updateHeat(post);
                XxlJobHelper.log("set post " + post.getId() + " heat");
                log.debug("set post {} heat", post.getId());
            }
            page++;
        } while (true);
        return new ReturnT<>(200, "success");
    }

    public IPage<PostVO> getPosts(int page, int pageSize) {
        PostFilterCondition condition = new PostFilterCondition();
        condition.setStatus(1);

        Page<Post> p = new Page<>();
        p.setCurrent(page).setSize(pageSize);
        return postService.getByCondition(p, condition);
    }
}
