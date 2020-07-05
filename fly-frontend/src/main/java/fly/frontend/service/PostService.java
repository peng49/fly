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
import javax.xml.stream.events.Comment;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

    public List<Post> findTop(int limit) {
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

    public List<Post> getEveryWeekCommentMax(int limit) {
        return postMapper.getEveryWeekCommentMax(limit);
    }

    public List<PostComment> getComments(int postId) {
        List<PostComment> comments = postMapper.getComments(postId);

        ArrayList<Integer> parentIds = new ArrayList<>();
        for (PostComment comment : comments) {
            if(comment.getParent() != null){
                parentIds.add(comment.getParent().getId());
            }
        }

        //去重
        ArrayList<Integer> ids = new ArrayList<>(new HashSet<>(parentIds));
        if (ids.size() > 0) {
            List<PostComment> parentComments = getCommentByCommentIds(parentIds);
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


        System.out.println(comments);
        return comments;
    }

    public List<PostComment> getCommentByCommentIds(ArrayList commentIds){
        return postMapper.getCommentsByCommentIds(commentIds);
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
}
