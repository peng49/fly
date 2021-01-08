package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.frontend.entity.model.UserBlacklist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBlacklistMapper extends BaseMapper<UserBlacklist> {

}
