package fly.frontend.service;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.pojo.UpdatePassword;
import fly.frontend.pojo.UpdateUserInfo;
import fly.frontend.pojo.UserLogin;
import fly.frontend.pojo.UserRegister;

import java.util.List;

public interface UserService {
    String LOGIN_KEY = "login-user";

    User add(User user);

    User getByUsername(String username);

    User login(UserLogin login) throws Exception;

    User register(UserRegister register) throws Exception;

    String getPassword(String password);

    boolean comparePassword(String password, String hash);

    User getById(int id);

    List<Post> getCollectionPosts(User user);

    User updateInfo(User user, UpdateUserInfo userInfo);

    User updateAvatar(User user, String avatar);

    void updatePassword(User user, UpdatePassword updatePassword) throws Exception;
}
