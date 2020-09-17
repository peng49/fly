package fly.frontend.service.impl;

import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.mapper.PostMapper;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Autowired
    private PostCommentService postCommentService;

    public List<Post> getByCondition(PostFilterCondition condition) {
        return postMapper.getByCondition(condition);
    }

    public List<Post> findTop(int limit) {
        return postMapper.findTop(limit);
    }

    public List<Post> findByColumnId(int columnId) {
        return postMapper.findByColumnId(columnId);
    }

    public List<Post> findAllByAuthorId(int id) {
        return postMapper.findAllByAuthorId(id);
    }

    public List<Post> findAllPublishByAuthorId(int id) {
        return postMapper.findAllPublishByAuthorId(id);
    }

    public Post get(int id) {
        return postMapper.get(id);
    }

    public Post create(PostEditFrom postEditFrom, User user) {
        System.out.println(postEditFrom);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postEditFrom.getTitle());
        post.setOriginalContent(postEditFrom.getOriginalContent());
        post.setContent(postEditFrom.getContent());

        Column column = new Column();
        column.setId(postEditFrom.getColumnId());
        post.setColumn(column);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(timestamp);
        post.setUpdateAt(timestamp);

        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostService.PUBLISH_STATUS);
            post.setPublishAt(timestamp);
            post.setHeat(calculationHeat(post));
        }

        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
    }

    public void updateHeat(Post post) {
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
            HashMap<Integer, PostComment> hashComments = new HashMap<>();
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

    public void top(Post post) {
        postMapper.top(post);
    }

    public void essence(Post post) {
        postMapper.essence(post);
    }

    public void edit(Post post, PostEditFrom postEditFrom) {
        post.setOriginalContent(postEditFrom.getOriginalContent());
        post.setContent(postEditFrom.getContent());
        post.setTitle(postEditFrom.getTitle());
        Column column = new Column();
        column.setId(postEditFrom.getColumnId());
        post.setColumn(column);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //发布
        if ("publish".equals(postEditFrom.getAction())) {
            post.setStatus(PostService.PUBLISH_STATUS);
            post.setPublishAt(timestamp);
            post.setUpdateAt(timestamp);
        }

        postMapper.edit(post);
    }

    /**
     * 计算文章热度
     *
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
