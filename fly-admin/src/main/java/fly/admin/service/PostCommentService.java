package fly.admin.service;

import fly.admin.entity.model.PostComment;
import fly.admin.entity.vo.PostCommentVO;

import java.util.List;

public interface PostCommentService {
    PostComment add(PostComment comment);

    void delete(PostComment comment);

    PostComment update(PostComment comment);

    PostComment findOne(int id);

    PostCommentVO get(int id);

    List<PostCommentVO> search();
}
