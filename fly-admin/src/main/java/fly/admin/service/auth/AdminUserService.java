package fly.admin.service.auth;

import fly.admin.entity.model.AdminUser;

import java.util.List;

public interface AdminUserService {
    AdminUser add(AdminUser user);

    void delete(AdminUser user);

    AdminUser update(AdminUser user);

    List<AdminUser> search();

    AdminUser get(int id);
}
