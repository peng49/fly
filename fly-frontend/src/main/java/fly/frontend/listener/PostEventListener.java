package fly.frontend.listener;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.UserPostArchive;
import fly.frontend.event.PostPublishEvent;
import fly.frontend.service.UserPostArchiveService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PostEventListener {
    @Resource
    private UserPostArchiveService userPostArchiveService;

    @EventListener
    public void execute(PostPublishEvent event) {
        Post post = event.getPost();

        /*** 归档 ***/
        Long userId = post.getAuthorId();

        List<UserPostArchive> items = userPostArchiveService.lambdaQuery().eq(UserPostArchive::getUserId, userId)
                .eq(UserPostArchive::getYear, 1)
                .eq(UserPostArchive::getMouth, 1)
                .list();

        if (items.size() > 0) {
            //更新
            UserPostArchive archive = items.get(0);
            archive.setPostCount(archive.getPostCount() + 1);
            userPostArchiveService.save(archive);
        } else {
            //新建
            UserPostArchive archive = new UserPostArchive();
            archive.setUserId(userId);
            archive.setYear(1);
            archive.setMouth(1);
            archive.setPostCount(1);
            userPostArchiveService.save(archive);
        }
    }
}
