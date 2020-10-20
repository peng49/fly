package fly.admin.service;

import fly.admin.entity.model.Post;
import fly.admin.entity.model.User;
import fly.admin.entity.vo.PostVO;
import fly.admin.entity.vo.UserVO;

import java.util.List;

public interface UserService {
    User add(User user);

    void delete(User user);

    User update(User user);

    User findOne(int id);

    UserVO get(int id);

    List<UserVO> search();
}
