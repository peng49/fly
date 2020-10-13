package fly.admin.service;

import fly.admin.entity.model.Column;

public interface ColumnService {
    Column add(Column column);

    void delete(Column column);

    Column update(Column column);

    Column get(int id);
}
