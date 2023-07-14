package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.PostComment;
import fly.web.entity.model.PostCommentAgree;
import fly.web.entity.model.User;

public interface PostCommentAgreeService extends IService<PostCommentAgree> {
    boolean exists(PostComment postComment, User user);

    void removeOrAdd(PostComment postComment, User user);
}
