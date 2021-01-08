package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserFollowMapper;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserFollow;
import fly.frontend.service.UserFollowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {
    @Override
    public boolean exists(User followUser) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return lambdaQuery()
                .eq(UserFollow::getUserId, user.getId())
                .eq(UserFollow::getFollowUserId, followUser.getId()).list().size() > 0;
    }

    @Override
    public void removeOrAdd(User followUser) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (exists(followUser)) {
            //remove
            lambdaUpdate().eq(UserFollow::getUserId, user.getId())
                    .eq(UserFollow::getFollowUserId, followUser.getId())
                    .remove();
        } else {
            //add
            save(
                    UserFollow.builder()
                            .userId(user.getId())
                            .followUserId(followUser.getId())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
        }
    }
}
