package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.User;
import fly.web.entity.model.UserCollection;

public interface UserCollectionService extends IService<UserCollection> {
    boolean exists(User user, Long postId);

    void removeOrAdd(User user, Long postId);
}
