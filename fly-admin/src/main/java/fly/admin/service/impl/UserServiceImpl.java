package fly.admin.service.impl;

import fly.admin.entity.model.User;
import fly.admin.entity.vo.ListResultVO;
import fly.admin.entity.vo.ResultVO;
import fly.admin.entity.vo.UserVO;
import fly.admin.repository.PostCommentRepository;
import fly.admin.repository.PostRepository;
import fly.admin.repository.UserRepository;
import fly.admin.service.PostService;
import fly.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SimpleDateFormat simpleDateFormat;

    @Resource
    private PostRepository postRepository;

    @Resource
    private PostCommentRepository postCommentRepository;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public UserVO get(int id) {
        User user = userRepository.getOne(id);

        return UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatar("http://localhost:8080"+user.getAvatar())
                .isAdmin(user.getIsAdmin())
                .signature(user.getSignature())
                .postCount(postRepository.countByAuthorId(user.getId()))
                .commentCount(postCommentRepository.countByUserId(user.getId()))
                .createdAt(user.getCreatedAt() == null ? null : simpleDateFormat.format(user.getCreatedAt()))
                .build();
    }

    @Override
    public ResultVO search(int page, int pageSize, Map<String, Object> query) {
        //构造查询条件
        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.get("keyword") != null) {
                Path<String> path = root.get("username");
                predicates.add(criteriaBuilder.like(path, "%" + query.get("keyword") + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Page<User> users = userRepository.findAll(specification, PageRequest.of(page - 1, pageSize));
        List<UserVO> items = new ArrayList<>();
        users.forEach(user -> {
            items.add(UserVO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .email(user.getEmail())
                    .avatar(user.getAvatar())
                    .isAdmin(user.getIsAdmin())
                    .signature(user.getSignature())
                    .createdAt(user.getCreatedAt() == null ? null : simpleDateFormat.format(user.getCreatedAt()))
                    .build());
        });
        return ResultVO.builder()
                .code("success")
                .message("Success")
                .data(
                        ListResultVO.builder()
                                .items(items)
                                .page(page)
                                .pageSize(pageSize)
                                .total(users.getTotalElements())
                                .build()
                ).build();
    }
}
