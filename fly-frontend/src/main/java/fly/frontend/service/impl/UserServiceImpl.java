package fly.frontend.service.impl;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.event.RegisteredEvent;
import fly.frontend.mapper.UserMapper;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;
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

    public User add(User user) {
        int row = userMapper.add(user);
        if (row == 0) {
            throw new RuntimeException("添加用户失败");
        }
        return user;
    }

    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    public User login(UserLoginFrom login) throws Exception {
        User user = getByUsername(login.getUsername());
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }

        if (comparePassword(login.getPassword(), user.getPassword())) {
            //密码错误
            throw new Exception("密码错误");
        }
        //登录成功
        return user;
    }

    public User register(UserRegisterFrom register) throws Exception {
        User user = new User();
        User existed = userMapper.getByUsername(register.getUsername());
        System.out.println(existed);
        if (existed != null) {
            throw new Exception("用户名已存在");
        }

        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(getPassword(register.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        userMapper.add(user);

        publisher.publishEvent(new RegisteredEvent(user));

        return user;
    }

    public String getPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePassword(String password, String hash) {
        return !bCryptPasswordEncoder.matches(password, hash);
    }


    public User getById(int id) {
        return userMapper.getById(id);
    }

    public List<Post> getCollectionPosts(User user) {
        User model = new User();
        model.setId(user.getId());
        return userPostService.findByUser(model);
    }

    public User updateInfo(User user, UpdateUserInfoFrom userInfo) {
        user.setEmail(userInfo.getEmail());
        user.setUsername(userInfo.getUsername());
        user.setCity(userInfo.getCity());
        user.setSignature(userInfo.getSignature());
        userMapper.updateInfo(user);
        return user;
    }

    public User updateAvatar(User user, String avatar) {
        user.setAvatar(avatar);
        userMapper.updateAvatar(user);
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
        userMapper.updatePassword(model);
    }
}
