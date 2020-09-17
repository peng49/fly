package fly.frontend.mapper;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserPostMapper {
    @Insert("insert into user_posts (user_id,post_id) values (#{user.id},#{postId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int create(User user, int postId);

    @Select("select count(0) from user_posts where user_id = #{userId} and post_id = #{postId} limit 1")
    boolean isExisted(int userId, int postId);

    @Delete("delete from user_posts where user_id = #{user.id} and post_id = #{postId}")
    void delete(User user, int postId);

    @Select("select p.*,c.name as column_name,u.username,u.avatar from user_posts as up" +
            " inner join posts as p on up.post_id = p.id " +
            " inner join users as u on u.id = p.author_id " +
            " left join columns as c on c.id = p.column_id " +
            "where up.user_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "column.id", column = "column_id"),
            @Result(property = "column.name", column = "column_name"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "status", column = "status"),
            @Result(property = "publishAt", column = "publish_at"),
            @Result(property = "author.id", column = "author_id"),
            @Result(property = "author.username", column = "username"),
            @Result(property = "author.avatar", column = "avatar")
    })
    List<Post> findByUser(User user);
}
