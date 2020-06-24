package fly.frontend.mapper;

import fly.frontend.entity.Post;
import fly.frontend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAll();

    @Insert("insert into users (username,email,password) values (#{username},#{email},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int create(User user);

    public User getByUsername(String username);

    public User getByEmail(String email);

    @Select("select * from users where id = #{id};")
    public User getById(int id);

    @Select("select * from user_posts as up inner join posts as p on up.post_id = p.id where up.user_id = #{userId}")
    public List<Post> findCollectionPosts(int userId);

    @Update("update users set email = #{email},username = #{username},city = #{city},signature = #{signature} where id = #{id};")
    public void updateInfo(User user);
}
