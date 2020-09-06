package fly.frontend.service.impl;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.event.CommentEvent;
import fly.frontend.mapper.PostCommentMapper;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Resource
    private PostService postService;

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private ApplicationEventPublisher publisher;

    public PostComment create(User user, PostCommentAdd postCommentAdd) {
        PostComment comment = new PostComment();
        comment.setCommentTime(new Date(System.currentTimeMillis()));
        comment.setContent(postCommentAdd.getContent());

        if (postCommentAdd.getParentId() != 0) {
            PostComment parentComment = this.get(postCommentAdd.getParentId());
            comment.setParent(parentComment);
        }

        Post post = postService.get(postCommentAdd.getPostId());
        comment.setPost(post);
        comment.setLevel(post.getReplyCount() + 1);
        comment.setUser(user);

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

    public PostComment get(int id) {
        return postCommentMapper.get(id);
    }
}
