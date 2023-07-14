package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.from.UpdatePasswordFrom;
import fly.web.entity.from.UpdateUserInfoFrom;
import fly.web.entity.from.UserLoginFrom;
import fly.web.entity.from.UserRegisterFrom;
import fly.web.entity.model.User;
import fly.web.entity.vo.UserVO;

public interface UserService extends IService<User> {
    String LOGIN_KEY = "login-user";

    UserVO login(UserLoginFrom login) throws Exception;

    User register(UserRegisterFrom register) throws Exception;

    User updateInfo(User user, UpdateUserInfoFrom userInfo);

    User updateAvatar(User user, String avatar);

    String getUniqueUsername(String name);

    void updatePassword(User user, UpdatePasswordFrom updatePassword) throws Exception;

    UserVO get(Long id);

    User getByUsername(String username);
}
