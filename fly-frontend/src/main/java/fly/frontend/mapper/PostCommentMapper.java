package fly.frontend.mapper;

import fly.frontend.entity.model.PostComment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostCommentMapper {
    @Insert("insert into post_comments(user_id,post_id,parent_id,level,content,comment_time) values(#{user.id},#{post.id},#{parent.id},#{level},#{content},#{commentTime})")
    int create(PostComment comment);

    @Select("select c.*,p.title as post_title from post_comments as c " +
            "inner join posts as p on p.id=c.post_id " +
            "where c.user_id = #{userId}")
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

    @Select("select c.*,p.title as post_title,u.username from post_comments as c " +
            "inner join posts as p on p.id=c.post_id " +
            "left join users as u on u.id = c.user_id " +
            "where c.id = #{id}")
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

    @Update("update post_comments set agree_count = agree_count - 1 where id = #{commentId}")
    void commentAgreeDec(int commentId);

    @Update("update post_comments set agree_count = agree_count + 1 where id = #{commentId}")
    void commentAgreeInc(int commentId);
}
