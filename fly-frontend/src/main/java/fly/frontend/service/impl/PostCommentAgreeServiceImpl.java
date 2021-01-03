package fly.frontend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.PostCommentAgreeMapper;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;
import fly.frontend.service.PostCommentAgreeService;
import fly.frontend.service.PostCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class PostCommentAgreeServiceImpl extends ServiceImpl<PostCommentAgreeMapper, PostCommentAgree> implements PostCommentAgreeService {

    @Resource
    private PostCommentService postCommentService;

    @Override
    public boolean exists(PostComment postComment, User user) {
        return lambdaQuery().eq(PostCommentAgree::getUserId, user.getId()).eq(PostCommentAgree::getPostCommentId, postComment.getId()).list().size() > 0;
    }

    @Override
    public void removeOrAdd(PostComment postComment, User user) {
        if(exists(postComment,user)){
            //remove
            lambdaUpdate().eq(PostCommentAgree::getUserId, user.getId()).eq(PostCommentAgree::getPostCommentId, postComment.getId()).remove();
            postCommentService.commentAgreeDec(postComment.getId());
        }else{
            //add
            save(PostCommentAgree.builder()
                    .postCommentId(postComment.getId())
                    .postId(postComment.getPostId())
                    .userId(user.getId())
                    .createdAt(LocalDateTime.now())
                    .build());
            postCommentService.commentAgreeInc(postComment.getId());
        }

    }
}
