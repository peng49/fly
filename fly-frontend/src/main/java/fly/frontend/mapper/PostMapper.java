package fly.frontend.mapper;

import fly.frontend.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("select p.*,u.username,u.avatar from posts as p inner join users as u on u.id = p.author_id;")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "columnId", column = "column_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "status", column = "status"),
            @Result(property = "publishAt", column = "publish_at"),
            @Result(property = "author.id",column = "author_id"),
            @Result(property = "author.username",column = "username"),
            @Result(property = "author.avatar",column = "avatar")
    })
    public List<Post> findAll();

    @Select("select p.*,u.username,u.avatar from posts as p inner join users as u on u.id = p.author_id where p.column_id = #{columnId};")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "columnId", column = "column_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "status", column = "status"),
            @Result(property = "publishAt", column = "publish_at"),
            @Result(property = "author.id",column = "author_id"),
            @Result(property = "author.username",column = "username"),
            @Result(property = "author.avatar",column = "avatar")
    })
    public List<Post> findByColumnId(int columnId);

    @Select("select * from posts as p where p.author_id = #{id} order by p.id desc;")
    public List<Post> findByAuthorId(int id);

    @Select("select p.*,u.username,u.avatar from posts as p inner join users as u on u.id = p.author_id where p.id = #{id};")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "columnId", column = "column_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "status", column = "status"),
            @Result(property = "publishAt", column = "publish_at"),
            @Result(property = "author.id",column = "author_id"),
            @Result(property = "author.username",column = "username"),
            @Result(property = "author.avatar",column = "avatar")
    })
    public Post findById(int id);



    public void create(Post post);

    public void update(Post post);
}
