package fly.frontend.service;

import fly.frontend.entity.Column;
import fly.frontend.mapper.ColumnMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ColumnService {
    @Resource
    private ColumnMapper columnMapper;

    public List<Column> getAll(){
        return columnMapper.getAll();
    }
}
