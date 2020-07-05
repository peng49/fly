package fly.frontend.listener;

import fly.frontend.entity.Post;
import fly.frontend.event.CommentEvent;
import fly.frontend.mapper.PostMapper;
import fly.frontend.service.PostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommentEventListener {
    @Resource
    private PostService postService;

    @EventListener
    public void postReplyCountInc(CommentEvent event) {
        System.out.println("comment post id:"+event.getPostComment().getPost().getId());
        postService.replyCountInc(event.getPostComment().getPost().getId());
    }
}
