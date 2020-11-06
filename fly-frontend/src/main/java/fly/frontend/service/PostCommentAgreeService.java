package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.PostCommentAgree;
import fly.frontend.entity.model.User;

public interface PostCommentAgreeService extends IService<PostCommentAgree> {
    boolean isExisted(User user,Long commentId);

    void delete(User user,Long commentId);
}
