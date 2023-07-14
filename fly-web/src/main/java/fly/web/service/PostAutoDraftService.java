package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.Post;
import fly.web.entity.model.PostAutoDraft;

public interface PostAutoDraftService extends IService<PostAutoDraft> {

    PostAutoDraft getForPost(Post post);

    PostAutoDraft getForUserId(Long id);
}
