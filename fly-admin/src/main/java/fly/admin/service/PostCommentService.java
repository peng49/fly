package fly.admin.service;

import fly.admin.entity.model.PostComment;
import fly.admin.entity.vo.PostCommentVO;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface PostCommentService {

    void delete(PostComment comment);

    PostComment findOne(int id);

    PostCommentVO get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
