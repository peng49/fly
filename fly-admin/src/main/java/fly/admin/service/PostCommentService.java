package fly.admin.service;

import fly.admin.entity.model.PostComment;
import fly.admin.entity.vo.PostCommentVO;
import fly.admin.entity.vo.ResultVO;

import java.util.List;

public interface PostCommentService {

    void delete(PostComment comment);

    PostComment findOne(int id);

    PostCommentVO get(int id);

    Object search(int page,int pageSize);
}
