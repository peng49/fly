package fly.frontend.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fly.frontend.entity.Column;
import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.event.CommentEvent;
import fly.frontend.mapper.PostMapper;
import fly.frontend.pojo.PostAdd;
import fly.frontend.pojo.PostCommentAdd;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private ApplicationEventPublisher publisher;

    public List<Post> findAll() {
        PageHelper.startPage(1, 10);
        List<Post> all = postMapper.findAll();
        PageHelper.clearPage();
        return all;
    }

    public List<Post> findTop(int limit)
    {
        return postMapper.findTop(4);
    }

    public List<Post> findByColumnId(int columnId) {
        return postMapper.findByColumnId(columnId);
    }

    public List<Post> findByAuthorId(int id) {
        return postMapper.findByAuthorId(id);
    }

    public Post findById(int id) {
        return postMapper.findById(id);
    }

    public Post create(PostAdd postAdd, User user) {
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postAdd.getTitle());
        post.setContent(postAdd.getContent());

        Column column = new Column();
        column.setId(postAdd.getColumnId());
        post.setColumn(column);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(timestamp);
        post.setUpdateAt(timestamp);
        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
    }

    public List<Post> getEveryWeekCommentMax(int limit)
    {
        return postMapper.getEveryWeekCommentMax(limit);
    }

    public List<PostComment> getComments(int postId) {
        return postMapper.getComments(postId);
    }

    public PostComment addComment(User user, PostCommentAdd postCommentAdd) {
        PostComment comment = new PostComment();
        comment.setCommentTime(new Date(System.currentTimeMillis()));
        comment.setContent(postCommentAdd.getContent());
        Post post = new Post();
        post.setId(postCommentAdd.getPostId());
        comment.setPost(post);
        comment.setUser(user);
        postMapper.addComment(comment);

        publisher.publishEvent(new CommentEvent(comment));
        return comment;
    }

    public void top(Post post){
        postMapper.top(post);
    }

    public void essence(Post post){
        postMapper.essence(post);
    }
}
