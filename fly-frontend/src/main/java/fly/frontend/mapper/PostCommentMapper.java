package fly.frontend.mapper;

import fly.frontend.entity.PostComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostCommentMapper {
    @Select("select c.*,p.title as post_title from post_comments as c inner join posts as p on p.id=c.post_id where c.user_id = #{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "commentTime", column = "comment_time"),
            @Result(property = "content", column = "content"),
            @Result(property = "content", column = "content"),
            @Result(property = "post.id", column = "post_id"),
            @Result(property = "post.title", column = "post_title")
    })
    List<PostComment> getByUserId(int userId);

    List<PostComment> getCommentsByCommentIds(ArrayList<Integer> commentIds);

    @Select("select c.*,p.title as post_title,u.username from post_comments as c inner join posts as p on p.id=c.post_id left join users as u on u.id = c.user_id where c.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "commentTime", column = "comment_time"),
            @Result(property = "content", column = "content"),
            @Result(property = "content", column = "content"),
            @Result(property = "post.id", column = "post_id"),
            @Result(property = "post.title", column = "post_title"),
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user.username", column = "username")
    })
    PostComment get(int id);
}
