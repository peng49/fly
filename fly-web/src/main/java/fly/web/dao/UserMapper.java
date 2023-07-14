package fly.web.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.web.entity.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
