package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.frontend.entity.model.UserPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPostMapper extends BaseMapper<UserPost> {

}
