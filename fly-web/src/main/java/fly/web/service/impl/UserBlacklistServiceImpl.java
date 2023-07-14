package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.UserBlacklistMapper;
import fly.web.entity.model.User;
import fly.web.entity.model.UserBlacklist;
import fly.web.service.UserBlacklistService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserBlacklistServiceImpl extends ServiceImpl<UserBlacklistMapper, UserBlacklist> implements UserBlacklistService {
    @Override
    public boolean exists(User blackUser) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return lambdaQuery()
                .eq(UserBlacklist::getUserId, user.getId())
                .eq(UserBlacklist::getBlackUserId, blackUser.getId()).list().size() > 0;
    }

    @Override
    public void removeOrAdd(User blackUser) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (exists(blackUser)) {
            //remove
            lambdaUpdate().eq(UserBlacklist::getUserId, user.getId())
                    .eq(UserBlacklist::getBlackUserId, blackUser.getId())
                    .remove();
        } else {
            //add
            save(
                    UserBlacklist.builder()
                            .userId(user.getId())
                            .blackUserId(blackUser.getId())
                            .createdAt(LocalDateTime.now())
                            .build()
            );
        }
    }
}
