package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;

public interface PostCommentAgreeService extends IService<PostCommentAgree> {
    boolean exists(PostComment postComment, User user);

    void removeOrAdd(PostComment postComment, User user);
}
