package fly.frontend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.from.PostCommentAddFrom;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.PostCommentVO;

import java.util.ArrayList;
import java.util.List;

public interface PostCommentService {
    PostComment create(Integer userId, PostCommentAddFrom postCommentAddFrom);

    PostComment get(int id);

    List<PostCommentVO> getByUserId(int userId);

    List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds);

    void commentAgreeInc(int commentId);

    void commentAgreeDec(int commentId);

    List<User> getUsersByContent(String content);

    IPage<PostCommentVO> getByPostId(Page<PostComment> page, Integer postId);
}
