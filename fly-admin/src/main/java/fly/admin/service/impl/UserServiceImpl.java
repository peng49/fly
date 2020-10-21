package fly.admin.service.impl;

import fly.admin.entity.model.User;
import fly.admin.entity.vo.UserVO;
import fly.admin.repository.UserRepository;
import fly.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SimpleDateFormat simpleDateFormat;

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
                .avatar(user.getAvatar())
                .isAdmin(user.getIsAdmin())
                .signature(user.getSignature())
                .createdAt(user.getCreatedAt() == null ? null : simpleDateFormat.format(user.getCreatedAt()))
                .build();
    }

    @Override
    public List<UserVO> search() {
        List<User> users = userRepository.findAll();
        List<UserVO> result = new ArrayList<>();
        users.forEach(user -> {
            result.add(UserVO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .avatar(user.getAvatar())
                    .isAdmin(user.getIsAdmin())
                    .signature(user.getSignature())
                    .createdAt(user.getCreatedAt() == null ? null : simpleDateFormat.format(user.getCreatedAt()))
                    .build());
        });
        return result;
    }
}
