package fly.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.PostAgreeMapper;
import fly.web.entity.model.PostAgree;
import fly.web.service.PostAgreeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostAgreeServiceImpl extends ServiceImpl<PostAgreeMapper, PostAgree> implements PostAgreeService {
    @Override
    public boolean exists(Long postId, Long userId) {
        return lambdaQuery()
                .eq(PostAgree::getPostId, postId)
                .eq(PostAgree::getUserId, userId)
                .list().size() > 0;
    }

    @Override
    public void removeOrAdd(Long postId, Long userId) {
        if (exists(postId, userId)) {
            lambdaUpdate().eq(PostAgree::getPostId, postId)
                    .eq(PostAgree::getUserId, userId)
                    .remove();
        } else {
            save(PostAgree.builder().postId(postId).userId(userId).createdAt(LocalDateTime.now()).build());
        }
    }
}
