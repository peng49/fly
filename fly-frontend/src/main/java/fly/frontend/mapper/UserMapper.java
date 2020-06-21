package fly.frontend.mapper;

import fly.frontend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAll();

    @Insert("insert into users (username,email,password) values (#{username},#{email},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int create(User user);

    public User getByUsername(String username);

    public User getByEmail(String email);
}
