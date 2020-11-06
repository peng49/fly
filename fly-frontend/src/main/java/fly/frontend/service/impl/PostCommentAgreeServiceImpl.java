package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostCommentAgreeMapper;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;
import fly.frontend.service.PostCommentAgreeService;
import org.springframework.stereotype.Service;

@Service
public class PostCommentAgreeServiceImpl extends ServiceImpl<PostCommentAgreeMapper, PostCommentAgree> implements PostCommentAgreeService {
    @Override
    public boolean isExisted(User user, Long commentId) {
        return getOne(lambdaQuery().eq(PostCommentAgree::getUserId, user.getId()).eq(PostCommentAgree::getPostCommentId, commentId)) != null;
    }

    @Override
    public void delete(User user, Long commentId) {
        lambdaUpdate().eq(PostCommentAgree::getUserId, user.getId()).eq(PostCommentAgree::getPostCommentId, commentId).remove();
    }
}
