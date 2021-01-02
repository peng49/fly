package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostAgreeMapper;
import fly.frontend.entity.model.PostAgree;
import fly.frontend.service.PostAgreeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostAgreeServiceImpl extends ServiceImpl<PostAgreeMapper, PostAgree> implements PostAgreeService {

    @Override
    public void removeOrAdd(Long postId, Long userId) {
        List<PostAgree> list = lambdaQuery()
                .eq(PostAgree::getPostId, postId)
                .eq(PostAgree::getUserId, userId)
                .list();

        if (list.size() > 0) {
            lambdaUpdate().eq(PostAgree::getPostId, postId)
                    .eq(PostAgree::getUserId, userId)
                    .remove();
        } else {
            save(PostAgree.builder().postId(postId).userId(userId).createdAt(LocalDateTime.now()).build());
        }
    }
}
