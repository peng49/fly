package fly.admin.service.auth;

import fly.admin.entity.model.AdminPermission;

public interface AdminPermissionService {
    AdminPermission add(AdminPermission permission);

    void delete(AdminPermission permission);

    AdminPermission update(AdminPermission permission);

    AdminPermission get(int id);
}
