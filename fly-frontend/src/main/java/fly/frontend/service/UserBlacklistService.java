package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserBlacklist;

public interface UserBlacklistService extends IService<UserBlacklist> {
    boolean exists(User blackUser);

    void removeOrAdd(User blackUser);
}
