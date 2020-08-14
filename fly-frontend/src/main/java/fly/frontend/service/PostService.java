package fly.frontend.service;

import fly.frontend.entity.Column;
import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.event.CommentEvent;
import fly.frontend.mapper.PostMapper;
import fly.frontend.pojo.PostEdit;
import fly.frontend.pojo.PostCommentAdd;
import fly.frontend.pojo.PostFilterCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class PostService {
    @Resource
    private PostMapper postMapper;

    @Autowired
    private PostCommentService postCommentService;

    @Resource
    private ApplicationEventPublisher publisher;

    public List<Post> getByCondition(PostFilterCondition condition) {
        List<Post> posts = postMapper.getByCondition(condition);
        return posts;
    }

    public List<Post> findTop(int limit) {
        return postMapper.findTop(limit);
    }

    public List<Post> findByColumnId(int columnId) {
        return postMapper.findByColumnId(columnId);
    }

    public List<Post> findByAuthorId(int id) {
        return postMapper.findByAuthorId(id);
    }

    public Post get(int id) {
        return postMapper.get(id);
    }

    public Post create(PostEdit postEdit, User user) {
        System.out.println(postEdit);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postEdit.getTitle());
        post.setOriginalContent(postEdit.getOriginalContent());
        post.setContent(postEdit.getContent());

        Column column = new Column();
        column.setId(postEdit.getColumnId());
        post.setColumn(column);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(timestamp);
        post.setUpdateAt(timestamp);
        post.setPublishAt(timestamp);

        post.setHeat(calculationHeat(post));

        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
    }

    public void updateHeat(Post post){
        postMapper.updateHeat(post);
    }

    public List<Post> getEveryWeekCommentMax(int limit) {
        return postMapper.getEveryWeekCommentMax(limit);
    }

    public void replyCountInc(int postId) {
        postMapper.replyCountInc(postId);
    }

    public void viewCountInc(int postId) {
        postMapper.viewCountInc(postId);
    }

    public List<PostComment> getComments(int postId) {
        List<PostComment> comments = postMapper.getComments(postId);

        ArrayList<Integer> parentIds = new ArrayList<>();
        for (PostComment comment : comments) {
            if (comment.getParent() != null) {
                parentIds.add(comment.getParent().getId());
            }
        }

        //去重
        ArrayList<Integer> ids = new ArrayList<>(new HashSet<>(parentIds));
        if (ids.size() > 0) {
            List<PostComment> parentComments = postCommentService.getCommentsByCommentIds(parentIds);
            HashMap<Integer, PostComment> hashComments = new HashMap();
            for (PostComment parentComment : parentComments) {
                hashComments.put(parentComment.getId(), parentComment);
            }

            for (PostComment comment : comments) {
                if (comment.getParent() != null) {
                    comment.setParent(hashComments.get(comment.getParent().getId()));
                }
            }
        }
        return comments;
    }

    public PostComment addComment(User user, PostCommentAdd postCommentAdd) {
        PostComment comment = new PostComment();
        comment.setCommentTime(new Date(System.currentTimeMillis()));
        comment.setContent(postCommentAdd.getContent());
        Post post = new Post();
        post.setId(postCommentAdd.getPostId());

        if (postCommentAdd.getParentId() != 0) {
            PostComment parentComment = new PostComment();
            parentComment.setId(postCommentAdd.getParentId());
            comment.setParent(parentComment);
        }
        comment.setPost(post);
        comment.setUser(user);
        postMapper.addComment(comment);

        publisher.publishEvent(new CommentEvent(comment));
        return comment;
    }

    public void top(Post post) {
        postMapper.top(post);
    }

    public void essence(Post post) {
        postMapper.essence(post);
    }

    public void edit(Post post, PostEdit postEdit) {
        post.setOriginalContent(postEdit.getOriginalContent());
        post.setContent(postEdit.getContent());
        post.setTitle(postEdit.getTitle());
        Column column = new Column();
        column.setId(postEdit.getColumnId());
        post.setColumn(column);
        System.out.println(post);
        postMapper.edit(post);
    }

    /**
     * 计算文章热度
     * @param post 文章
     * @return 热度值
     */
    public double calculationHeat(Post post) {
        int start = 10;

        // 1*click + 5*favor + 10*comment + 20*share

        //100个查看等于一个回复
        double user = post.getViewCount() * 0.01 + post.getReplyCount();

        double time = (System.currentTimeMillis() - post.getPublishAt().getTime()) / 1000.00 / 3600.00;

        if (time <= 1) {
            time = 1.00;
        }

        return (start + user) / time;
    }
}
