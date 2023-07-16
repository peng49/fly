package fly.admin.repository;

import fly.admin.entity.model.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, Integer>, JpaSpecificationExecutor<SystemConfig> {
    SystemConfig findByAttribute(String attribute);
}
