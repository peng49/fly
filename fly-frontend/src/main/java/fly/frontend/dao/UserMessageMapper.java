package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {
    
}
