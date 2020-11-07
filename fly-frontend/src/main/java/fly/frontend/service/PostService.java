package fly.frontend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.from.PostEditFrom;
import fly.frontend.entity.from.PostFilterCondition;
import fly.frontend.entity.vo.PostVO;

import java.util.*;

public interface PostService extends IService<Post> {
    double DEFAULT_HEAD = 10.0;

    /**
     * 草稿状态
     */
    int DRAFT_STATUS = 0;

    /**
     * 发布状态
     */
    int PUBLISH_STATUS = 1;

    int DELETE_STATUS = 2;

    /**
     * 允许排序的字段
     */
    List<String> ALLOW_ORDER_FIELD = Arrays.asList("heat", "publish_at", "reply_count");

    IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition condition);

    IPage<PostVO> findAllByAuthorId(IPage<Post> page, Long id);

    IPage<PostVO> findUserPost(IPage page, Long userId);

    List<Post> findAllPublishByAuthorId(Long id);

    PostVO get(Long id);

    Post create(PostEditFrom postEditFrom, Long userId);

    void update(Post post);

    void updateHeat(Post post);

    void replyCountInc(Long postId);

    void viewCountInc(Long postId);

    void top(Post post);

    void essence(Post post);

    void edit(Post post, PostEditFrom postEditFrom);

    double calculationHeat(Post post);

    void move2delete(Post post);
}
