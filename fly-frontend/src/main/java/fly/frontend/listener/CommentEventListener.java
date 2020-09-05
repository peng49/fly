package fly.frontend.listener;

import fly.frontend.entity.PostComment;
import fly.frontend.entity.UserMessage;
import fly.frontend.event.CommentEvent;
import fly.frontend.service.PostService;
import fly.frontend.service.UserMessageService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Component
public class CommentEventListener {
    @Resource
    private PostService postService;

    @Resource
    private UserMessageService userMessageService;

    @EventListener
    public void execute(CommentEvent event) {
        PostComment postComment = event.getPostComment();

        //文章的评论数加1
        postService.replyCountInc(postComment.getPost().getId());

        //添加评论发送消息
        UserMessage commentMessage = new UserMessage();
        commentMessage.setType("comment");
        commentMessage.setSender(postComment.getUser());
        commentMessage.setReceiver(postService.get(postComment.getPost().getId()).getAuthor());
        commentMessage.setContent(postComment.getContent());
        commentMessage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userMessageService.create(commentMessage);

        //回复评论发送消息
        if (postComment.getParent() != null) {
            UserMessage userMessage = new UserMessage();
            userMessage.setType("reply");
            userMessage.setSender(postComment.getUser());
            userMessage.setReceiver(postComment.getParent().getUser());
            userMessage.setContent(postComment.getContent());
            userMessage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userMessageService.create(userMessage);
        }
    }
}
