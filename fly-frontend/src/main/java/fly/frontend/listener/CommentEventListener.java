package fly.frontend.listener;

import fly.frontend.event.CommentEvent;
import fly.frontend.service.PostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommentEventListener {
    @Resource
    private PostService postService;

    @EventListener
    public void execute(CommentEvent event) {
        System.out.println("comment post id:"+event.getPostComment().getPost().getId());
        //文章的评论数加1
        postService.replyCountInc(event.getPostComment().getPost().getId());

        //todo 是否需要发送消息
    }
}
