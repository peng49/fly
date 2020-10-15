package fly.admin.repository;

import fly.admin.entity.model.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionRepository extends JpaRepository<AdminPermission,Integer> {

}
