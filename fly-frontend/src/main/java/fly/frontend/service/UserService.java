package fly.frontend.service;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;

import java.util.List;

public interface UserService {
    String LOGIN_KEY = "login-user";

    User add(User user);

    User getByUsername(String username);

    User login(UserLoginFrom login) throws Exception;

    User register(UserRegisterFrom register) throws Exception;

    String getPassword(String password);

    boolean comparePassword(String password, String hash);

    User getById(int id);

    List<Post> getCollectionPosts(User user);

    User updateInfo(User user, UpdateUserInfoFrom userInfo);

    User updateAvatar(User user, String avatar);

    void updatePassword(User user, UpdatePasswordFrom updatePassword) throws Exception;
}
