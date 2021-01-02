package fly.frontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fly.frontend.entity.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    @Select("select p.* from posts as p inner join user_posts as up on up.post_id = p.id where up.user_id = #{userId} order by up.created_at desc")
    IPage<Post> findForUserCollection(Long userId, IPage page);

    @Select("select count(0) from posts as p inner join user_posts as up on up.post_id = p.id where up.user_id = #{userId}")
    int findUserCollectionTotal(Long userId);
}
