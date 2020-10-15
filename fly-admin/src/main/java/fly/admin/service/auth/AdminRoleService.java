package fly.admin.service.auth;

import fly.admin.entity.model.AdminRole;


public interface AdminRoleService {
    AdminRole add(AdminRole role);

    void delete(AdminRole role);

    AdminRole update(AdminRole role);

    AdminRole get(int id);
}
