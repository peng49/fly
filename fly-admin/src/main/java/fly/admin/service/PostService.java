package fly.admin.service;

import fly.admin.entity.model.Post;

import java.util.List;

public interface PostService {
    Post add(Post post);

    void delete(Post column);

    Post update(Post column);

    Post get(int id);

    List<Post> search();
}
