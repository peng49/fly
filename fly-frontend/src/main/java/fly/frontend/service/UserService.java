package fly.frontend.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.entity.vo.UserVO;

import java.util.List;

public interface UserService<U extends BaseMapper<UserMessage>> {
    String LOGIN_KEY = "login-user";

    UserVO login(UserLoginFrom login) throws Exception;

    User register(UserRegisterFrom register) throws Exception;

    User updateInfo(User user, UpdateUserInfoFrom userInfo);

    User updateAvatar(User user, String avatar);

    String getUniqueUsername(String name);

    void updatePassword(User user, UpdatePasswordFrom updatePassword) throws Exception;

    UserVO get(int id);

    User getByUsername(String username);
}
