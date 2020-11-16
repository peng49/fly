package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.UserPost;

public interface UserPostService extends IService<UserPost> {
    boolean create(Long userId,Long postId);

    void delete(Long userId,Long postId);

    boolean isExisted(Long userId, Long postId);
}
