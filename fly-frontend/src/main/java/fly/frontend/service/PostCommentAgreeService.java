package fly.frontend.service;

import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;

public interface PostCommentAgreeService {
    PostCommentAgree create(PostCommentAgree postCommentAgree);

    boolean isExisted(User user,int commentId);

    void delete(User user,int commentId);
}
