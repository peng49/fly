package fly.frontend.service;

import fly.frontend.entity.po.Column;

import java.util.List;

public interface ColumnService {
    List<Column> getAll();

    Column get(int id);
}
