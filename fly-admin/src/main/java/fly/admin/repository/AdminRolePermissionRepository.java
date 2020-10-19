package fly.admin.repository;

import fly.admin.entity.model.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRolePermissionRepository extends JpaRepository<AdminRolePermission, Integer> {
    void deleteByRoleId(int roleId);
}
