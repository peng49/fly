package fly.frontend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.from.PostCommentAddFrom;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.PostCommentVO;

import java.util.List;

public interface PostCommentService extends IService<PostComment> {
    PostComment create(Long userId, PostCommentAddFrom postCommentAddFrom);

    List<PostCommentVO> getByUserId(IPage<PostComment> page, Long userId);

    void commentAgreeInc(Long commentId);

    void commentAgreeDec(Long commentId);

    List<User> getUsersByContent(String content);

    IPage<PostCommentVO> getByPostId(Page<PostComment> page, Long postId);
}
