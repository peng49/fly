package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.User;
import fly.web.entity.model.UserBlacklist;

public interface UserBlacklistService extends IService<UserBlacklist> {
    boolean exists(User blackUser);

    void removeOrAdd(User blackUser);
}
