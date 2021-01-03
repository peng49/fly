package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.PostAgree;

public interface PostAgreeService extends IService<PostAgree> {
    boolean exists(Long postId, Long userId);

    void removeOrAdd(Long postId, Long userId);
}
