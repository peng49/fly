package fly.frontend.mapper;

import fly.frontend.entity.model.Post;
import fly.frontend.entity.model.PostAutoDraft;
import fly.frontend.entity.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostAutoDraftMapper {
    @Insert("insert into post_auto_draft(user_id,post_id,title,content,created_at,update_at) values (#{user.id},#{post.id},#{title},#{content},now(),now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(PostAutoDraft draft);

    @Select("select pad.*,u.username from post_auto_draft as pad inner join users as u on u.id = pad.user_id where pad.user_id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "content",column = "content"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "updateAt",column = "update_at"),
            @Result(property = "post.id",column = "post_id"),
            @Result(property = "user.id",column = "user_id"),
            @Result(property = "user.username",column = "username")
    })
    List<PostAutoDraft> getForUser(User user);

    @Select("select pad.*,u.username from post_auto_draft as pad inner join posts as p on p.id = pad.post_id where pad.post_id = #{id} order by pad.id desc limit 1")
    PostAutoDraft getForPost(Post post);
}
