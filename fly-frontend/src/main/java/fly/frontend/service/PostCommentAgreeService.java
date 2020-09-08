package fly.frontend.service;

import fly.frontend.entity.PostCommentAgree;
import fly.frontend.entity.User;

public interface PostCommentAgreeService {
    PostCommentAgree create(PostCommentAgree postCommentAgree);

    boolean isExisted(User user,int commentId);

    void delete(User user,int commentId);
}
