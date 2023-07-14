package fly.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.web.entity.model.UserMessage;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {
    
}
