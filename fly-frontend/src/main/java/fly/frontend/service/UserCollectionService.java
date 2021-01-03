package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserCollection;

public interface UserCollectionService extends IService<UserCollection> {
    boolean exists(User user, Long postId);

    void removeOrAdd(User user, Long postId);
}
