package fly.admin.service;

import fly.admin.entity.model.Column;
import fly.admin.entity.vo.ResultVO;

import java.util.Map;

public interface ColumnService {
    Column add(Column column);

    void delete(Column column);

    Column update(Column column);

    Column get(int id);

    ResultVO search(int page, int pageSize, Map<String,Object> query);
}
