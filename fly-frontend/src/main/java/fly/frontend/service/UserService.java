package fly.frontend.service;

import fly.frontend.entity.User;
import fly.frontend.mapper.UserMapper;
import fly.frontend.pojo.UserLogin;
import fly.frontend.pojo.UserRegister;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User login(UserLogin login) throws Exception {
        User user = userMapper.getByUsername(login.getUsername());
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }

        if (!user.getPassword().equals(login.getPassword())) {
            //密码错误
            throw new Exception("密码错误");
        }
        //登录成功
        return user;
    }

    public User register(UserRegister register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());
        int id = userMapper.create(user);
        System.out.println(id);
        return user;
    }

    public User getById(int id) {
        return userMapper.getById(id);
    }
}
