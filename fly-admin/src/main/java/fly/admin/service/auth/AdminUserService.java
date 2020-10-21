package fly.admin.service.auth;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.vo.UserLoginVO;

import java.util.List;

public interface AdminUserService {
    AdminUser add(AdminUser user);

    void delete(AdminUser user);

    AdminUser update(AdminUser user);

    List<?> search();

    AdminUser get(int id);

    UserLoginVO login(String username, String password);
}
