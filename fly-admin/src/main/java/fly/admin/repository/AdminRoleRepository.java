package fly.admin.repository;

import fly.admin.entity.model.AdminPermission;
import fly.admin.entity.model.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRoleRepository extends JpaRepository<AdminRole, Integer> {

}
