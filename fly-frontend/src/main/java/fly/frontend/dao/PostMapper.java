package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostComment;
import fly.frontend.entity.from.PostFilterCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
