package fly.frontend.service;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;

import java.util.List;

public interface UserPostService {
    int create(User user,int postId);

    void delete(User user,int postId);

    boolean isExisted(int userId, int postId);

    List<Post> findByUser(User user);
}
