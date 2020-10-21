package fly.admin.service.impl;

import fly.admin.entity.model.Post;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public Post add(Post post) {
        return null;
    }

    @Override
    public void delete(Post column) {

    }

    @Override
    public Post update(Post column) {
        return null;
    }

    @Override
    public Post findOne(int id) {
        return null;
    }

    @Override
    public PostVO get(int id) {
        return null;
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String,Object> query) {
        return null;
    }
}
