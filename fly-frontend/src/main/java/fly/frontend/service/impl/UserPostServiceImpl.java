package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserPostMapper;
import fly.frontend.entity.model.UserPost;
import fly.frontend.service.UserPostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements UserPostService {

    @Override
    public boolean create(Long userId, Long postId) {
        return save(UserPost.builder()
                .userId(userId)
                .postId(postId)
                .createdAt(LocalDateTime.now())
                .build());
    }

    @Override
    public void delete(Long userId, Long postId) {
        lambdaUpdate().eq(UserPost::getPostId, postId)
                .eq(UserPost::getUserId, userId)
                .remove();
    }

    @Override
    public boolean isExisted(Long userId, Long postId) {
        return lambdaQuery()
                .eq(UserPost::getUserId, userId)
                .eq(UserPost::getPostId, postId).list().size() > 0;
    }
}
