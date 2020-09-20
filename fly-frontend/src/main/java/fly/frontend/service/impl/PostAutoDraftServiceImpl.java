package fly.frontend.service.impl;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;
import fly.frontend.mapper.PostAutoDraftMapper;
import fly.frontend.service.PostAutoDraftService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostAutoDraftServiceImpl implements PostAutoDraftService {

    @Resource
    private PostAutoDraftMapper postAutoDraftMapper;

    @Override
    public PostAutoDraft add(PostAutoDraft draft) {
        int row = postAutoDraftMapper.add(draft);
        if (row == 0) {
            throw new RuntimeException("添加草稿失败");
        }
        return draft;
    }

    @Override
    public PostAutoDraft getForUser(User user) {
        return postAutoDraftMapper.getForUser(user);
    }

    @Override
    public PostAutoDraft getForPost(Post post) {
        return postAutoDraftMapper.getForPost(post);
    }

    @Override
    public PostAutoDraft update(PostAutoDraft postAutoDraft, PostAutoDraft draft) {
        draft.setId(postAutoDraft.getId());
        postAutoDraftMapper.update(draft);
        return draft;
    }

    @Override
    public void delete(int id) {
        postAutoDraftMapper.delete(id);
    }
}
