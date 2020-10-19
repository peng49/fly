package fly.admin.service.auth;

import fly.admin.entity.model.AdminRole;
import fly.admin.entity.request.EditAdminRoleRequest;
import fly.admin.entity.vo.AdminRoleVO;

import java.util.List;


public interface AdminRoleService {
    AdminRole add(EditAdminRoleRequest request);

    void delete(Integer id);

    AdminRole update(AdminRole role, EditAdminRoleRequest request);

    AdminRole findOne(int id);

    AdminRoleVO get(int id);

    List<AdminRoleVO> search();
}
