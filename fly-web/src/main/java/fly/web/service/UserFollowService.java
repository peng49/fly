package fly.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.User;
import fly.web.entity.model.UserFollow;

public interface UserFollowService extends IService<UserFollow> {
    boolean exists(User followUser);

    void removeOrAdd(User followUser);
}
