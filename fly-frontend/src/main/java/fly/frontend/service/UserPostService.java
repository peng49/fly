package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserPost;

import java.util.List;

public interface UserPostService extends IService<UserPost> {
    boolean create(User user,Long postId);

    void delete(User user,Long postId);

    boolean isExisted(Long userId, Long postId);

    List<Post> findByUserId(Long id);
}
