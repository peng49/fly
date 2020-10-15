package fly.admin.service.auth;

import fly.admin.entity.model.AdminUser;

public interface AdminUserService {
    AdminUser add(AdminUser user);

    void delete(AdminUser user);

    AdminUser update(AdminUser user);

    AdminUser get(int id);
}
