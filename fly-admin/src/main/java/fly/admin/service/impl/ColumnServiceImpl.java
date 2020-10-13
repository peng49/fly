package fly.admin.service.impl;

import fly.admin.entity.model.Column;
import fly.admin.repository.ColumnRepository;
import fly.admin.service.ColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ColumnServiceImpl implements ColumnService {

    @Resource
    private ColumnRepository columnRepository;

    @Override
    public Column add(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public void delete(Column column) {
        columnRepository.delete(column);
    }

    @Override
    public Column update(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public Column get(int id) {
        return columnRepository.getOne(id);
    }
}
