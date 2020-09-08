package fly.frontend.service;

import fly.frontend.entity.PostComment;
import fly.frontend.entity.User;
import fly.frontend.pojo.PostCommentAdd;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

public interface PostCommentService {
    PostComment create(User user,PostCommentAdd postCommentAdd);

    PostComment get(int id);

    List<PostComment> getByUserId(int userId);

    List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds);

    void commentAgreeInc(int commentId);

    void commentAgreeDec(int commentId);
}
