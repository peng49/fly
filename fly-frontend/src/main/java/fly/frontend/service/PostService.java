package fly.frontend.service;

import fly.frontend.entity.Post;
import fly.frontend.mapper.PostMapper;
import fly.frontend.pojo.PostAdd;
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

    public Post create(PostAdd postAdd) {
        Post post = new Post();
        post.setColumnId(postAdd.getColumnId());
        post.setAuthorId(postAdd.getUserId());
        post.setTitle(postAdd.getTitle());
        post.setContent(postAdd.getContent());
        postMapper.create(post);
        return post;
    }

    public void update(Post post) {
        postMapper.update(post);
    }
}
