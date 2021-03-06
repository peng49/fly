package fly.admin.service.auth;

import fly.admin.entity.model.AdminUser;
import fly.admin.entity.request.EditAdminUserRequest;
import fly.admin.entity.vo.UserLoginVO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface AdminUserService {
    AdminUser add(AdminUser user, EditAdminUserRequest request);

    void delete(AdminUser user);

    AdminUser update(AdminUser user,EditAdminUserRequest request);

    List<?> search();

    AdminUser get(int id);

    Collection<? extends GrantedAuthority> getAuthorities(AdminUser user);

    UserLoginVO login(String username, String password);
}
