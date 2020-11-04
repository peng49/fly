package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fly.frontend.dao.UserMapper;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.event.RegisteredEvent;
import fly.frontend.service.UserPostService;
import fly.frontend.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPostService userPostService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ApplicationEventPublisher publisher;


    public UserVO login(UserLoginFrom login) throws Exception {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, login.getUsername()));
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }

        if (comparePassword(login.getPassword(), user.getPassword())) {
            //密码错误
            throw new Exception("密码错误");
        }
        //登录成功
        return UserVO.builder().id(user.getId()).username(user.getUsername()).avatar(user.getAvatar()).build();
    }

    public User register(UserRegisterFrom register) throws Exception {
        User user = new User();
        User existed = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, register.getUsername()));
        System.out.println(existed);
        if (existed != null) {
            throw new Exception("用户名已存在");
        }

        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(getPassword(register.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        userMapper.insert(user);

        publisher.publishEvent(new RegisteredEvent(user));

        return user;
    }

    private String getPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean comparePassword(String password, String hash) {
        return !bCryptPasswordEncoder.matches(password, hash);
    }

    public User updateInfo(User user, UpdateUserInfoFrom userInfo) {
        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setCity(userInfo.getCity());
        user.setSignature(userInfo.getSignature());
        userMapper.updateById(user);
        return user;
    }

    public User updateAvatar(User user, String avatar) {
        user.setAvatar(avatar);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public String getUniqueUsername(String name) {
        int counter = 0;
        while (true) {
            User user = getByUsername(name);
            if (user == null || counter > 5) {
                break;
            }
            name = name + '_' + RandomStringUtils.randomAlphabetic(10);
            counter++;
        }
        return name;
    }

    public void updatePassword(User user, UpdatePasswordFrom updatePassword) throws Exception {
        //验证原密码是否正确
        if (comparePassword(updatePassword.getOldPassword(), user.getPassword())) {
            throw new Exception("原密码错误");
        }
        if (!updatePassword.getPassword().equals(updatePassword.getConfirmPassword())) {
            throw new Exception("新密码和确认密码不一致");
        }
        //修改密码
        user.setPassword(getPassword(updatePassword.getPassword()));

        User model = new User();
        model.setId(user.getId());
        model.setPassword(getPassword(updatePassword.getPassword()));
        userMapper.updateById(model);
    }

    @Override
    public UserVO get(int id) {
        User user = userMapper.selectById(id);
        return UserVO.builder()
                .id(user.getId())
                .avatar(user.getAvatar())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
    }
}
