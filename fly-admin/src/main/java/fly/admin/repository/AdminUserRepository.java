package fly.admin.repository;

import fly.admin.entity.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

}
