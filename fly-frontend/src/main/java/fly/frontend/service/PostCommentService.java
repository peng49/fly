package fly.frontend.service;

import fly.frontend.entity.po.PostComment;
import fly.frontend.entity.po.User;
import fly.frontend.pojo.PostCommentAdd;

import java.util.ArrayList;
import java.util.List;

public interface PostCommentService {
    PostComment create(User user,PostCommentAdd postCommentAdd);

    PostComment get(int id);

    List<PostComment> getByUserId(int userId);

    List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds);

    void commentAgreeInc(int commentId);

    void commentAgreeDec(int commentId);

    List<User> getUsersByContent(String content);
}
