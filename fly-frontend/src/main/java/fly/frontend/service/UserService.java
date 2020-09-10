package fly.frontend.service;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import fly.frontend.event.RegisteredEvent;
import fly.frontend.mapper.UserMapper;
import fly.frontend.pojo.UpdatePassword;
import fly.frontend.pojo.UpdateUserInfo;
import fly.frontend.pojo.UserLogin;
import fly.frontend.pojo.UserRegister;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    public final static String LOGIN_KEY = "login-user";

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPostService userPostService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ApplicationEventPublisher publisher;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User login(UserLogin login) throws Exception {
        User user = userMapper.getByUsername(login.getUsername());
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }

        if (!comparePassword(login.getPassword(), user.getPassword())) {
            //密码错误
            throw new Exception("密码错误");
        }
        //登录成功
        return user;
    }

    public User register(UserRegister register) throws Exception {
        User user = new User();
        User existed = userMapper.getByUsername(register.getUsername());
        System.out.println(existed);
        if (existed != null) {
            throw new Exception("用户名已存在");
        }

        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(getPassword(register.getPassword()));
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));

        userMapper.create(user);

        publisher.publishEvent(new RegisteredEvent(user));

        return user;
    }

    public String getPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePassword(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }


    public User getById(int id) {
        return userMapper.getById(id);
    }

    public List<Post> getCollectionPosts(User user) {
        return userPostService.findByUser(user);
    }

    public User updateInfo(User user, UpdateUserInfo userInfo) {
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

    public void updatePassword(User user, UpdatePassword updatePassword) throws Exception {
        //验证原密码是否正确
        if (!comparePassword(updatePassword.getOldPassword(), user.getPassword())) {
            throw new Exception("原密码错误");
        }
        if (!updatePassword.getPassword().equals(updatePassword.getConfirmPassword())) {
            throw new Exception("新密码和确认密码不一致");
        }
        //修改密码
        user.setPassword(getPassword(updatePassword.getPassword()));
        userMapper.updatePassword(user);
    }
}
