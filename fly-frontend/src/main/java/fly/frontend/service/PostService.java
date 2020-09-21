package fly.frontend.service;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;

import java.util.*;

public interface PostService {
    int DEFAULT_HEAD = 10;

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

    Post create(PostEditFrom postEditFrom, User user);

    void update(Post post);

    void updateHeat(Post post);

    List<Post> getEveryWeekCommentMax(int limit);

    void replyCountInc(int postId);

    void viewCountInc(int postId);

    List<PostComment> getComments(int postId);

    void top(Post post);

    void essence(Post post);

    void edit(Post post, PostEditFrom postEditFrom);

    double calculationHeat(Post post);
}
