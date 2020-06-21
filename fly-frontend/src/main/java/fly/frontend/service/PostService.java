package fly.frontend.service;

import fly.frontend.entity.Post;
import fly.frontend.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostService {
    @Resource
    private PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public Post findById(int id) {
        return postMapper.findById(id);
    }

    public void create(Post post) {
        postMapper.create(post);
    }

    public void update(Post post) {
        postMapper.update(post);
    }
}
