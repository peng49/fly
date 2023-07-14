package fly.web.listener;

import fly.web.entity.model.Post;
import fly.web.entity.model.UserPostArchive;
import fly.web.event.PostPublishEvent;
import fly.web.service.UserPostArchiveService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
        LocalDateTime time = LocalDateTime.now();

        List<UserPostArchive> items = userPostArchiveService.lambdaQuery().eq(UserPostArchive::getUserId, userId)
                .eq(UserPostArchive::getYear, time.getYear())
                .eq(UserPostArchive::getMonth, time.getMonthValue())
                .list();

        System.out.println(items);

        if (items.size() > 0) {
            //更新
            UserPostArchive archive = items.get(0);
            archive.setPostCount(archive.getPostCount() + 1);
            userPostArchiveService.updateById(archive);
        } else {
            //新建
            UserPostArchive archive = new UserPostArchive();
            archive.setUserId(userId);
            archive.setYear(time.getYear());
            archive.setMonth(time.getMonthValue());
            archive.setPostCount(1);
            userPostArchiveService.save(archive);
        }
    }
}
