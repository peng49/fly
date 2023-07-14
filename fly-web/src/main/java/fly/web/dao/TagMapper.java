package fly.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.web.entity.model.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
