package fly.frontend.service;

import fly.frontend.entity.Column;
import fly.frontend.mapper.ColumnMapper;
import org.apache.ibatis.annotations.Select;
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


    public  Column get(int id){
        return columnMapper.get(id);
    }
}
