package fly.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.PostAutoDraftMapper;
import fly.web.entity.model.Post;
import fly.web.entity.model.PostAutoDraft;
import fly.web.service.PostAutoDraftService;
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
