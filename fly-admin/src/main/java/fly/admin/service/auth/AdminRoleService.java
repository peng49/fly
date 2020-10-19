package fly.admin.service.auth;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.request.EditAdminRoleRequest;

import java.util.List;


public interface AdminRoleService {
    AdminRole add(EditAdminRoleRequest request);

    void delete(AdminRole role);

    AdminRole update(AdminRole role, EditAdminRoleRequest request);

    AdminRole get(int id);

    List<AdminRole> search();
}
