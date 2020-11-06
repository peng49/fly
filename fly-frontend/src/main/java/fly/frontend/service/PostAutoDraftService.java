package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;

public interface PostAutoDraftService extends IService<PostAutoDraft> {

    PostAutoDraft getForPost(Post post);

    PostAutoDraft getForUserId(Long id);
}
