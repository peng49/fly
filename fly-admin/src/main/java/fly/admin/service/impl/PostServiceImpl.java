package fly.admin.service.impl;

import fly.admin.entity.dto.UserDTO;
import fly.admin.entity.model.Column;
import fly.admin.entity.model.Post;
import fly.admin.entity.model.User;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.repository.ColumnRepository;
import fly.admin.repository.PostRepository;
import fly.admin.repository.UserRepository;
import fly.admin.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private ColumnRepository columnRepository;

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
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        Page<Post> posts = postRepository.findAll(PageRequest.of(page - 1, pageSize));
        List<PostVO> items = new ArrayList<>();

        ArrayList<Integer> userIds = new ArrayList<>();
        ArrayList<Integer> columnIds = new ArrayList<>();
        posts.forEach(post -> {
            userIds.add(post.getAuthorId());
            columnIds.add(post.getColumnId());
        });

        List<User> users = userRepository.findUsersByIdIn(userIds);
        List<Column> columns = columnRepository.findColumnsByIdIn(columnIds);

        Map<Integer, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        Map<Integer, Column> columnMap = columns.stream()
                .collect(Collectors.toMap(Column::getId, Function.identity()));

        posts.forEach(post -> {
            User user = userMap.get(post.getAuthorId());
            Column column = columnMap.get(post.getColumnId());

            items.add(PostVO.builder()
                    .id(post.getId())
                    .author(UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .name(user.getName())
                            .build())
                    .column(column)
                    .title(post.getTitle())
                    .status(post.getStatus())
                    .essence(post.getEssence())
                    .heat(post.getHeat())
                    .viewCount(post.getViewCount())
                    .replyCount(post.getReplyCount())
                    .publishAt(post.getPublishAt())
                    .createdAt(post.getCreatedAt())
                    .updateAt(post.getUpdateAt())
                    .build());
        });

        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        ListResultVO.builder()
                                .items(items)
                                .pageSize(pageSize)
                                .page(page)
                                .total(posts.getTotalElements())
                                .build()
                ).build();
    }
}
