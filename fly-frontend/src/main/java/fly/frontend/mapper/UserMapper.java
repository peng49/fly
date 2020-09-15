package fly.frontend.mapper;


import fly.frontend.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into users (username,email,password,avatar,create_time) values (#{username},#{email},#{password},#{avatar},#{createTime,jdbcType=TIMESTAMP})")
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
