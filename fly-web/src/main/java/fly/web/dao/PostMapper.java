package fly.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import fly.web.entity.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    @Select("select p.* from posts as p inner join user_collection as uc on uc.post_id = p.id where uc.user_id = #{userId} order by uc.created_at desc")
    IPage<Post> findForUserCollection(Long userId, IPage<?> page);

    @Select("select count(0) from posts as p inner join user_collection as uc on uc.post_id = p.id where uc.user_id = #{userId}")
    int findUserCollectionTotal(Long userId);
}
