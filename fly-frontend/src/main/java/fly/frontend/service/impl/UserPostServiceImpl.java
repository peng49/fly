package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.dao.UserPostMapper;
import fly.frontend.entity.model.UserPost;
import fly.frontend.service.UserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPostServiceImpl implements UserPostService {
    @Resource
    private UserPostMapper userPostMapper;

    @Resource
    private PostMapper postMapper;

    @Override
    public int create(User user, int postId) {
        return userPostMapper.insert(UserPost.builder()
                .userId((long) user.getId())
                .postId((long) postId)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Override
    public void delete(User user, int postId) {
        userPostMapper.delete(Wrappers.<UserPost>lambdaQuery()
                .eq(UserPost::getPostId, postId)
                .eq(UserPost::getUserId, user.getId())
        );
    }

    @Override
    public boolean isExisted(int userId, int postId) {
        return userPostMapper.selectOne(Wrappers.lambdaQuery(UserPost.class)
                .eq(UserPost::getUserId, userId)
                .eq(UserPost::getPostId, postId)) != null;
    }

    @Override
    public List<Post> findByUserId(Integer userId) {
        List<UserPost> list = userPostMapper.selectList(Wrappers.lambdaQuery(UserPost.class).eq(UserPost::getUserId, userId));
        List<Long> postIds = list.stream().map(UserPost::getPostId).collect(Collectors.toList());
        return postMapper.selectList(Wrappers.lambdaQuery(Post.class).in(Post::getId, postIds));
    }
}
