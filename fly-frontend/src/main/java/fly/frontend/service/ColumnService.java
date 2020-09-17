package fly.frontend.service;

import fly.frontend.entity.model.Column;

import java.util.List;

public interface ColumnService {
    List<Column> getAll();

    Column get(int id);
}
