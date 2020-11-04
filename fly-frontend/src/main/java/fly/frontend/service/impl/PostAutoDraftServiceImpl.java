package fly.frontend.service.impl;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;
import fly.frontend.dao.PostAutoDraftMapper;
import fly.frontend.service.PostAutoDraftService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostAutoDraftServiceImpl implements PostAutoDraftService {

    @Resource
    private PostAutoDraftMapper postAutoDraftMapper;

    @Override
    public PostAutoDraft add(PostAutoDraft draft) {
        if(draft.getPost() == null){
            draft.setPost(Post.builder().id(0).build());//post_id 默认设置为0
        }

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
    public PostAutoDraft update(PostAutoDraft draft) {
        postAutoDraftMapper.update(draft);
        return draft;
    }

    @Override
    public void delete(int id) {
        postAutoDraftMapper.delete(id);
    }
}
