package fly.frontend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserFollow;

public interface UserFollowService extends IService<UserFollow> {
    boolean exists(User followUser);

    void removeOrAdd(User followUser);
}
