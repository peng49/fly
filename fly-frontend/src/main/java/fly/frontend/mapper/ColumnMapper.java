package fly.frontend.mapper;

import fly.frontend.entity.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ColumnMapper {
    @Select("select * from columns")
    List<Column> getAll();

    @Select("select * from columns where id = #{id}")
    Column get(int id);
}
