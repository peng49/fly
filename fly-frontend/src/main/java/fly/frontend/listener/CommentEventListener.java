package fly.frontend.listener;

import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.event.CommentEvent;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserMessageService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Component
public class CommentEventListener {
    @Resource
    private PostService postService;

    @Resource
    private UserMessageService userMessageService;

    @Resource
    private PostCommentService postCommentService;

    @EventListener
    public void execute(CommentEvent event) {
        PostComment postComment = event.getPostComment();

        //添加评论发送消息
        UserMessage commentMessage = new UserMessage();
        commentMessage.setType("comment");
        commentMessage.setSenderId(postComment.getUserId());

        commentMessage.setReceiverId(postService.get(postComment.getPostId()).getAuthor().getId());
        commentMessage.setContent(postComment.getContent());
        commentMessage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userMessageService.create(commentMessage);

        //获取评论 @ 的所有用户
        List<User> users = postCommentService.getUsersByContent(postComment.getContent());
        for (User user : users) {
            if (user.getId() == postComment.getUserId()) {
                //@自己不用发送信息
                continue;
            }
            UserMessage userMessage = new UserMessage();
            userMessage.setType("reply");
            userMessage.setSenderId(postComment.getUserId());
            userMessage.setReceiverId(user.getId());
            userMessage.setContent(postComment.getContent());
            userMessage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userMessageService.create(userMessage);
        }
    }
}
