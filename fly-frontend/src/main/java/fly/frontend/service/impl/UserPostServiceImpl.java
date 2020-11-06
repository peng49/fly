package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostMapper;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.dao.UserPostMapper;
import fly.frontend.entity.model.UserPost;
import fly.frontend.service.PostService;
import fly.frontend.service.UserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements UserPostService {

    @Resource
    private PostService postService;

    @Override
    public boolean create(User user, Long postId) {
        return save(UserPost.builder()
                .userId(user.getId())
                .postId(postId)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Override
    public void delete(User user, Long postId) {
        lambdaUpdate().eq(UserPost::getPostId, postId)
                .eq(UserPost::getUserId, user.getId())
                .remove();
    }

    @Override
    public boolean isExisted(Long userId, Long postId) {
        return getOne(lambdaQuery()
                .eq(UserPost::getUserId, userId)
                .eq(UserPost::getPostId, postId)) != null;
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        List<UserPost> list = list(Wrappers.lambdaQuery(UserPost.class).eq(UserPost::getUserId, userId));
        List<Long> postIds = list.stream().map(UserPost::getPostId).collect(Collectors.toList());
        return postService.list(Wrappers.lambdaQuery(Post.class).in(Post::getId, postIds));
    }
}
