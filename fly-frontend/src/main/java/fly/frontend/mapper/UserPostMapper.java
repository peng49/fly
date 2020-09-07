package fly.frontend.mapper;

import fly.frontend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserPostMapper {
    @Insert("insert into user_posts (user_id,post_id) values (#{user.id},#{postId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int create(User user, int postId);

    @Select("select count(0) from user_posts where user_id = #{userId} and post_id = #{postId} limit 1")
    boolean isExisted(int userId, int postId);
}
