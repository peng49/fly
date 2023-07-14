package fly.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.from.PostCommentAddFrom;
import fly.web.entity.model.PostComment;
import fly.web.entity.model.User;
import fly.web.entity.vo.PostCommentVO;

import java.util.List;

public interface PostCommentService extends IService<PostComment> {
    PostComment create(Long userId, PostCommentAddFrom postCommentAddFrom);

    IPage<PostCommentVO> getByUserId(IPage<PostComment> page, Long userId);

    void commentAgreeInc(Long commentId);

    void commentAgreeDec(Long commentId);

    List<User> getUsersByContent(String content);

    IPage<PostCommentVO> getByPostId(Page<PostComment> page, Long postId);
}
