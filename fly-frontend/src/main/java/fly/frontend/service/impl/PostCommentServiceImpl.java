package fly.frontend.service.impl;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.event.CommentEvent;
import fly.frontend.mapper.PostCommentMapper;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Resource
    private PostService postService;

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private UserService userService;

    @Resource
    private ApplicationEventPublisher publisher;

    private String parseCommentContent(String content) {
        //获取所有有效的用户
        List<User> users = getUsersByContent(content);

        //将所有的@用户替换为a标签
        for (User user : users) {
            content = content.replaceAll(
                    "@" + user.getUsername(),
                    "@<a href='/u/" + user.getId() + "'>" + user.getUsername() + "</a> "
            );
        }
        return content;
    }

    public PostComment create(User user, PostCommentAdd postCommentAdd) {
        PostComment comment = new PostComment();
        comment.setCommentTime(new Date(System.currentTimeMillis()));

        //解析内容
        comment.setContent(parseCommentContent(postCommentAdd.getContent()));

        Post post = postService.get(postCommentAdd.getPostId());

        if (post == null || post.getStatus() != 1) {
            throw new RuntimeException("文章还未发布，不能进行评论");
        }

        comment.setPost(post);
        comment.setLevel(post.getReplyCount() + 1);
        comment.setUser(user);

        if (postCommentAdd.getParentId() != 0) {
            PostComment parentComment = this.get(postCommentAdd.getParentId());
            comment.setParent(parentComment);
        }

        //文章的评论数加1
        postService.replyCountInc(comment.getPost().getId());

        postCommentMapper.create(comment);

        publisher.publishEvent(new CommentEvent(comment));
        return comment;
    }

    public List<PostComment> getByUserId(int userId) {
        return postCommentMapper.getByUserId(userId);
    }

    public List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds) {
        return postCommentMapper.getCommentsByCommentIds(commentIds);
    }

    @Override
    public void commentAgreeInc(int commentId) {
        postCommentMapper.commentAgreeInc(commentId);
    }

    @Override
    public void commentAgreeDec(int commentId) {
        postCommentMapper.commentAgreeDec(commentId);
    }

    @Override
    public List<User> getUsersByContent(String content) {
        String regex = "@(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(StringUtils.html2text(content));

        List<User> users = new ArrayList<>();
        while (matcher.find()) {
            String username = matcher.group(1).trim();
            User user = userService.getByUsername(username);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    public PostComment get(int id) {
        return postCommentMapper.get(id);
    }
}
