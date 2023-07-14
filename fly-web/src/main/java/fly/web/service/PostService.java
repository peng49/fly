package fly.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.from.PostEditFrom;
import fly.web.entity.from.PostFilterCondition;
import fly.web.entity.model.Post;
import fly.web.entity.vo.PostVO;

import java.util.Arrays;
import java.util.List;

public interface PostService extends IService<Post> {
    double DEFAULT_HEAD = 10.0;

    /**
     * 允许排序的字段
     */
    List<String> ALLOW_ORDER_FIELD = Arrays.asList("heat", "publish_at", "reply_count");

    IPage<PostVO> getByCondition(Page<Post> page, PostFilterCondition condition);

    IPage<PostVO> findAllByAuthorId(IPage<Post> page, Long id);

    IPage<PostVO> findUserPost(IPage<Object> page, Long userId);

    IPage<Post> findPublishByAuthorId(Long id,IPage<Post> page);

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
