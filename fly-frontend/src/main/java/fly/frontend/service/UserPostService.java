package fly.frontend.service;

import fly.frontend.entity.User;

public interface UserPostService {
    int create(User user,int postId);


    boolean isExisted(int userId, int postId);
}
