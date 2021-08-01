package fly.admin.repository;

import fly.admin.entity.model.Navigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NavigationRepository extends JpaRepository<Navigation,Integer>, JpaSpecificationExecutor<Navigation> {

}
