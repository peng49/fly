package fly.admin.service.auth;

import fly.admin.entity.model.AdminPermission;

import java.util.List;

public interface AdminPermissionService {
    String METHOD_SPLIT = ",";

    String PATH_SPLIT = "\n";

    AdminPermission add(AdminPermission permission);

    void delete(AdminPermission permission);

    AdminPermission update(AdminPermission permission);

    AdminPermission get(int id);

    List<AdminPermission> search();
}
