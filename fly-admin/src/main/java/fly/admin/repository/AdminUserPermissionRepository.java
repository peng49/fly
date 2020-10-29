package fly.admin.repository;

import fly.admin.entity.model.AdminUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserPermissionRepository extends JpaRepository<AdminUserPermission, Integer> {
    void deleteByUserId(int userId);

    List<AdminUserPermission> findByUserIdIn(List<Integer> userIds);
}
