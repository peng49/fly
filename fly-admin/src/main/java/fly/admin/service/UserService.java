package fly.admin.service;

import fly.admin.entity.model.User;
import fly.admin.entity.vo.ResultVO;
import fly.admin.entity.vo.UserVO;

import java.util.Map;


public interface UserService {
    User add(User user);

    void delete(User user);

    User update(User user);

    User findOne(Long id);

    UserVO get(Long id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
