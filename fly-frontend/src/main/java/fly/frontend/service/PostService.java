package fly.frontend.service;

import fly.frontend.entity.Column;
import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostEdit;
import fly.frontend.pojo.PostFilterCondition;

import java.sql.Timestamp;
import java.util.*;

public interface PostService {

    /**
     * 草稿状态
     */
    int DRAFT_STATUS = 0;

    /**
     * 发布状态
     */
    int PUBLISH_STATUS = 1;

    /**
     * 允许排序的字段
     */
    List<String> ALLOW_ORDER_FIELD = Arrays.asList("heat", "publish_at", "reply_count");

    List<Post> getByCondition(PostFilterCondition condition);

    List<Post> findTop(int limit);

    List<Post> findByColumnId(int columnId);

    List<Post> findAllByAuthorId(int id);

    List<Post> findAllPublishByAuthorId(int id);

    Post get(int id);

    Post create(PostEdit postEdit, User user);

    void update(Post post);

    void updateHeat(Post post);

    List<Post> getEveryWeekCommentMax(int limit);

    void replyCountInc(int postId);

    void viewCountInc(int postId);

    List<PostComment> getComments(int postId);

    void top(Post post);

    void essence(Post post);

    void edit(Post post, PostEdit postEdit);

    double calculationHeat(Post post);
}
