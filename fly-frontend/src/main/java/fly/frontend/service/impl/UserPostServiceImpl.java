package fly.frontend.service.impl;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.mapper.UserPostMapper;
import fly.frontend.service.UserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPostServiceImpl implements UserPostService {
    @Resource
    private UserPostMapper userPostMapper;

    @Override
    public int create(User user,int postId) {
        return userPostMapper.create(user,postId);
    }

    @Override
    public void delete(User user, int postId) {
        userPostMapper.delete(user,postId);
    }

    @Override
    public boolean isExisted(int userId, int postId) {
        return userPostMapper.isExisted(userId, postId);
    }

    @Override
    public List<Post> findByUser(User user) {
        return userPostMapper.findByUser(user);
    }
}
