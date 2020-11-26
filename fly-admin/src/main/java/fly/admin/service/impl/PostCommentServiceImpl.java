package fly.admin.service.impl;

import fly.admin.entity.dto.PostDTO;
import fly.admin.entity.dto.UserDTO;
import fly.admin.entity.model.Post;
import fly.admin.entity.model.PostComment;
import fly.admin.entity.model.User;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.PostCommentVO;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.PostCommentRepository;
import fly.admin.repository.PostRepository;
import fly.admin.repository.UserRepository;
import fly.admin.service.PostCommentService;
import fly.admin.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Resource
    private PostCommentRepository postCommentRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PostRepository postRepository;

    @Resource
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public void delete(PostComment comment) {
        postCommentRepository.delete(comment);
    }

    @Override
    public PostComment findOne(Long id) {
        return postCommentRepository.getOne(id);
    }

    @Override
    public PostCommentVO get(Long id) {
        PostComment comment = findOne(id);
        return PostCommentVO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }

    @Override
    public ResultVO search(int page, int pageSize,Map<String, Object> query) {
        Page<PostComment> comments = postCommentRepository.findAll(PageRequest.of(page - 1, pageSize));
        List<PostCommentVO> items = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();
        List<Long> postIds = new ArrayList<>();

        comments.forEach(comment -> {
            userIds.add(comment.getUserId());
            postIds.add(comment.getPostId());
        });

        List<Post> posts = postRepository.findPostsByIdIn(postIds);
        posts.forEach(post -> {
            userIds.add(post.getAuthorId());
        });

        List<User> users = userRepository.findUsersByIdIn(userIds);

        Map<Long, Post> postMap = posts.stream()
                .collect(Collectors.toMap(Post::getId, Function.identity()));

        Map<Long, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        comments.forEach(comment -> {
            Post post = postMap.get(comment.getPostId());
            User user = userMap.get(comment.getUserId());//评论人
            User author = userMap.get(post.getAuthorId());// 文章作者
            items.add(PostCommentVO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .user(UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .email(user.getEmail())
                            .build())
                    .post(PostDTO.builder()
                            .id(post.getId())
                            .publishAt(post.getPublishAt())
                            .title(post.getTitle())
                            .author(UserDTO.builder()
                                    .id(author.getId())
                                    .username(author.getUsername())
                                    .name(author.getName())
                                    .email(author.getEmail())
                                    .build())
                            .build())
                    .commentTime(comment.getCreatedAt() == null ? null : dateTimeFormatter.format(comment.getCreatedAt()))
                    .build());
        });

        return ResultVO.builder()
                .code("success")
                .message("Success").data(
                        ListResultVO.builder()
                                .items(items)
                                .page(comments.getPageable().getPageNumber() + 1)
                                .pageSize(comments.getPageable().getPageSize())
                                .total(comments.getTotalElements())
                                .build()
                ).build();
    }
}
