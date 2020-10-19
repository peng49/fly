package fly.admin.repository;

import fly.admin.entity.model.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRolePermissionRepository extends JpaRepository<AdminRolePermission, Integer> {
    void deleteByRoleId(int roleId);

    List<AdminRolePermission> findByRoleId(Integer roleId);


    List<AdminRolePermission> findByRoleIdIn(List<Integer> roleIds);
}
