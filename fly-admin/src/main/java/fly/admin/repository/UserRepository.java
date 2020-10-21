package fly.admin.repository;

import fly.admin.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUsersByIdIn(List<Integer> userIds);
}
