package fly.frontend.service;

import fly.frontend.entity.PostComment;
import fly.frontend.mapper.PostCommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostCommentService {
    @Resource
    private PostCommentMapper postCommentMapper;


    public List<PostComment> getByUserId(int userId) {
        return postCommentMapper.getByUserId(userId);
    }

    public List<PostComment> getCommentsByCommentIds(ArrayList commentIds){
        return postCommentMapper.getCommentsByCommentIds(commentIds);
    }
}
