package fly.admin.repository;

import fly.admin.entity.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Integer> {
    List<Column> findColumnsByIdIn(List<Integer> ids);
}
