package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostAutoDraftMapper;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.service.PostAutoDraftService;
import org.springframework.stereotype.Service;

@Service
public class PostAutoDraftServiceImpl extends ServiceImpl<PostAutoDraftMapper, PostAutoDraft> implements PostAutoDraftService {

    @Override
    public PostAutoDraft getForPost(Post post) {
        return getOne(Wrappers.lambdaQuery(PostAutoDraft.class).
                eq(PostAutoDraft::getUserId, post.getAuthorId())
                .eq(PostAutoDraft::getPostId, post.getId()), false);
    }

    @Override
    public PostAutoDraft getForUserId(Long userId) {
        return getOne(Wrappers.lambdaQuery(PostAutoDraft.class).
                eq(PostAutoDraft::getUserId, userId)
                .eq(PostAutoDraft::getPostId, 0), false);
    }
}
