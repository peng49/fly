package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.PostAgree;

public interface PostAgreeService extends IService<PostAgree> {
    boolean exists(Long postId, Long userId);

    void removeOrAdd(Long postId, Long userId);
}
