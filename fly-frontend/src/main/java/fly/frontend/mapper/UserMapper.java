package fly.frontend.mapper;


import fly.frontend.entity.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into users (username,email,password,avatar,created_at) values (#{username},#{email},#{password},#{avatar},#{createdAt,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(User user);

    User getByUsername(String username);

    User getByEmail(String email);

    @Select("select * from users where id = #{id};")
    User getById(int id);

    @Update("update users set email = #{email},username = #{username},city = #{city},signature = #{signature} where id = #{id}")
    void updateInfo(User user);

    @Update("update users set avatar = #{avatar} where id = #{id}")
    void updateAvatar(User user);

    @Update("update users set password = #{password} where id = #{id}")
    void updatePassword(User user);
}
