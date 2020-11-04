package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.frontend.entity.model.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ColumnMapper extends BaseMapper<Column> {
    @Select("select * from columns")
    List<Column> getAll();

    @Select("select * from columns where id = #{id}")
    Column get(int id);
}
