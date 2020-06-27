package fly.frontend.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.mapper.PostMapper;
import fly.frontend.pojo.PostAdd;
import fly.frontend.pojo.PostCommentAdd;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class PostService {
    @Resource
    private PostMapper postMapper;

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
        post.setColumnId(postAdd.getColumnId());
        post.setAuthor(user);
        post.setTitle(postAdd.getTitle());
        post.setContent(postAdd.getContent());
        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
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
        return comment;
    }
}
