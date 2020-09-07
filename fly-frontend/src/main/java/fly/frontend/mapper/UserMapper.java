package fly.frontend.mapper;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    @Insert("insert into users (username,email,password,create_time) values (#{username},#{email},#{password},#{createTime,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(User user);

    User getByUsername(String username);

    User getByEmail(String email);

    @Select("select * from users where id = #{id};")
    User getById(int id);

    @Select("select * from user_posts as up inner join posts as p on up.post_id = p.id where up.user_id = #{userId}")
    List<Post> findCollectionPosts(int userId);

    @Update("update users set email = #{email},username = #{username},city = #{city},signature = #{signature} where id = #{id}")
    void updateInfo(User user);

    @Update("update users set avatar = #{avatar} where id = #{id}")
    void updateAvatar(User user);

    @Update("update users set password = #{password} where id = #{id}")
    void updatePassword(User user);
}
