package fly.admin.service;

import fly.admin.entity.model.Post;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface PostService {
    Post add(Post post);

    void delete(Post column);

    Post update(Post column);

    Post findOne(int id);

    PostVO get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
