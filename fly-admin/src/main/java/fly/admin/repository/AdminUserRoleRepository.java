package fly.admin.repository;

import fly.admin.entity.model.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleRepository extends JpaRepository<AdminUserRole, Integer> {
    void deleteByUserId(int userId);

    List<AdminUserRole> findByUserIdIn(List<Integer> userIds);
}
