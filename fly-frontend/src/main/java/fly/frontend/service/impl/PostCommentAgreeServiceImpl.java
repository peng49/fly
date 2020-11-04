package fly.frontend.service.impl;

import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;
import fly.frontend.dao.PostCommentAgreeMapper;
import fly.frontend.service.PostCommentAgreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostCommentAgreeServiceImpl implements PostCommentAgreeService {

    @Resource
    private PostCommentAgreeMapper postCommentAgreeMapper;

    @Override
    public PostCommentAgree create(PostCommentAgree postCommentAgree) {
        int res = postCommentAgreeMapper.create(postCommentAgree);
        if (res != 1) {
            throw new RuntimeException("新增数据失败");
        }
        return postCommentAgree;
    }

    @Override
    public boolean isExisted(User user, int commentId) {
        return postCommentAgreeMapper.isExisted(user, commentId);
    }

    @Override
    public void delete(User user, int commentId) {
        postCommentAgreeMapper.delete(user, commentId);
    }
}
