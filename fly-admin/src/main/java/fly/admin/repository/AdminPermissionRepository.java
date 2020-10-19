package fly.admin.repository;

import fly.admin.entity.model.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminPermissionRepository extends JpaRepository<AdminPermission,Integer> {
    List<AdminPermission> findByIdIn(List<Integer> ids);
//    List<AdminPermission> findAdminPermissionsByIdsIn(List<Integer> ids);
}
