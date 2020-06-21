package fly.frontend.mapper;

import fly.frontend.entity.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ColumnMapper {
    @Select("select * from columns;")
    public List<Column> getAll();
}
