package fly.admin.service.auth;

import fly.admin.entity.model.AdminPermission;

import java.util.List;

public interface AdminPermissionService {
    AdminPermission add(AdminPermission permission);

    void delete(AdminPermission permission);

    AdminPermission update(AdminPermission permission);

    AdminPermission get(int id);

    List<AdminPermission> search();
}
