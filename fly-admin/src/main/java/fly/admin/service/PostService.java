package fly.admin.service;

import fly.admin.entity.model.Post;
import fly.admin.entity.vo.PostVO;

import java.util.List;

public interface PostService {
    Post add(Post post);

    void delete(Post column);

    Post update(Post column);

    Post findOne(int id);

    PostVO get(int id);

    List<PostVO> search();
}
