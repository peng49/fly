package fly.admin.service.impl;

import fly.admin.entity.model.Post;
import fly.admin.entity.model.PostComment;
import fly.admin.entity.vo.PostCommentVO;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.PostCommentRepository;
import fly.admin.service.PostCommentService;
import fly.admin.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Resource
    private PostCommentRepository postCommentRepository;

    @Override
    public void delete(PostComment comment) {
        postCommentRepository.delete(comment);
    }

    @Override
    public PostComment findOne(int id) {
        return postCommentRepository.getOne(id);
    }

    @Override
    public PostCommentVO get(int id) {
        PostComment comment = findOne(id);
        return PostCommentVO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }

    @Override
    public ResultVO search(int page,int pageSize) {
        Page<PostComment> comments = postCommentRepository.findAll(PageRequest.of(page - 1, pageSize));
        List<PostCommentVO> items = new ArrayList<>();
        comments.forEach(comment -> {
            items.add(PostCommentVO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .commentTime(comment.getCommentTime())
                    .build());
        });

        HashMap<Object, Object> hash = new HashMap<>();
        hash.put("items",items);
        hash.put("page",comments.getPageable().getPageNumber());
        hash.put("pageSize",comments.getPageable().getPageSize());
        hash.put("total",comments.getTotalElements());

        return ResultVO.builder()
                .code("success")
                .message("Success").data(hash).build();
    }
}
