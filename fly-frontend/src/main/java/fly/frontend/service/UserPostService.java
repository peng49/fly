package fly.frontend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserPost;
import fly.frontend.entity.vo.PostVO;

import java.util.List;

public interface UserPostService extends IService<UserPost> {
    boolean create(User user,Long postId);

    void delete(User user,Long postId);

    boolean isExisted(Long userId, Long postId);
}
