package fly.frontend.service.impl;

import fly.frontend.entity.model.Column;
import fly.frontend.mapper.ColumnMapper;
import fly.frontend.service.ColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    @Resource
    private ColumnMapper columnMapper;

    public List<Column> getAll() {
        return columnMapper.getAll();
    }

    public Column get(int id) {
        return columnMapper.get(id);
    }
}
