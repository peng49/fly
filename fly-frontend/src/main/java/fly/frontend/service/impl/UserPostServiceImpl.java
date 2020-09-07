package fly.frontend.service.impl;

import fly.frontend.entity.User;
import fly.frontend.mapper.UserPostMapper;
import fly.frontend.service.UserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
