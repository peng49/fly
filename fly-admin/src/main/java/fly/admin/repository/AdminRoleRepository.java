package fly.admin.repository;

import fly.admin.entity.model.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleRepository extends JpaRepository<AdminRole, Integer> {

    List<AdminRole> findAllByIdIn(Integer[] id);
}
