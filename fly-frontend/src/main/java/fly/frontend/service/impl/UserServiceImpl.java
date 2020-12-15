package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.frontend.dao.UserMapper;
import fly.frontend.entity.from.UpdatePasswordFrom;
import fly.frontend.entity.from.UpdateUserInfoFrom;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.from.UserRegisterFrom;
import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.UserVO;
import fly.frontend.event.RegisteredEvent;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.PostCommentService;
import fly.frontend.service.PostService;
import fly.frontend.service.UserService;
import fly.frontend.utils.AvatarUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ApplicationEventPublisher publisher;

    @Resource
    private PostService postService;

    @Resource
    private DateTimeFormatter dateTimeFormatter;

    @Resource
    private PostCommentService postCommentService;

    @Resource
    private OauthAccountService oauthAccountService;

    @Value("${user.avatar-dir}")
    private String userDir;


    public UserVO login(UserLoginFrom login) throws Exception {
        User user = getByUsername(login.getUsername());
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

    private String getRandomAvatarUrl() {
        BufferedImage bi = new AvatarUtils().getARandomAvatar();
        String filename = UUID.randomUUID() + ".png";
        File file = new File(userDir + "/" + filename);
        try {
            ImageIO.write(bi, "PNG", file);
            return "/static/" + filename;
        } catch (IOException e) {
            return "";
        }
    }

    public User register(UserRegisterFrom register) throws Exception {

        User existed = getByUsername(register.getUsername());
        System.out.println(existed);
        if (existed != null) {
            throw new Exception("用户名已存在");
        }
        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(getPassword(register.getPassword()));
        user.setAvatar(getRandomAvatarUrl());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        save(user);

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
        updateById(user);
        return user;
    }

    public User updateAvatar(User user, String avatar) {
        user.setAvatar(avatar);
        updateById(user);
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
        updateById(model);
    }

    @Override
    public UserVO get(Long id) {
        User user = getById(id);
        return UserVO.builder()
                .id(user.getId())
                .registerAt(dateTimeFormatter.format(user.getCreatedAt()))
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .username(user.getUsername())
                .city(user.getCity())
                .signature(user.getSignature())
                .commentCount(
                        postCommentService.lambdaQuery()
                                .eq(PostComment::getUserId, user.getId())
                                .count()
                )
                .publishCount(
                        postService.lambdaQuery()
                                .eq(Post::getStatus, PostService.PUBLISH_STATUS)
                                .eq(Post::getAuthorId, user.getId())
                                .count()
                )
                .bindPlatform(
                       oauthAccountService.lambdaQuery()
                                .eq(OauthAccount::getUserId, user.getId())
                                .list()
                                .stream()
                                .map(OauthAccount::getPlatform)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public User getByUsername(String username) {
        return getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), false);
    }
}
