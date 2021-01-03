package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserCollectionMapper;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserCollection;
import fly.frontend.service.UserCollectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {
    @Override
    public boolean exists(User user, Long postId) {
        return lambdaQuery()
                .eq(UserCollection::getUserId, user.getId())
                .eq(UserCollection::getPostId, postId).list().size() > 0;
    }

    @Override
    public void removeOrAdd(User user, Long postId) {
        Long userId = user.getId();
        if(exists(user,postId)){
            //remove
            lambdaUpdate().eq(UserCollection::getPostId, postId)
                    .eq(UserCollection::getUserId, userId)
                    .remove();
        }else{
            //todo 验证postId是否是有效的文章Id

            //add
            save(UserCollection.builder()
                    .userId(userId)
                    .postId(postId)
                    .createdAt(LocalDateTime.now())
                    .build());
        }
    }
}
