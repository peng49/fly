package fly.frontend.service;

import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.from.PostCommentAddFrom;

import java.util.ArrayList;
import java.util.List;

public interface PostCommentService {
    PostComment create(User user, PostCommentAddFrom postCommentAddFrom);

    PostComment get(int id);

    List<PostComment> getByUserId(int userId);

    List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds);

    void commentAgreeInc(int commentId);

    void commentAgreeDec(int commentId);

    List<User> getUsersByContent(String content);
}
