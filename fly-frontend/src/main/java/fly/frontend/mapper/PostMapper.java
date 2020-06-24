package fly.frontend.mapper;

import fly.frontend.entity.Post;
import fly.frontend.entity.PostComment;
import org.apache.ibatis.annotations.*;

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
            @Result(property = "author.id", column = "author_id"),
            @Result(property = "author.username", column = "username"),
            @Result(property = "author.avatar", column = "avatar")
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
            @Result(property = "author.id", column = "author_id"),
            @Result(property = "author.username", column = "username"),
            @Result(property = "author.avatar", column = "avatar")
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
            @Result(property = "author.id", column = "author_id"),
            @Result(property = "author.username", column = "username"),
            @Result(property = "author.avatar", column = "avatar")
    })
    public Post findById(int id);


    public void create(Post post);

    public void update(Post post);

    @Select("select pc.*,u.username,u.avatar from post_comments as pc inner join users u on u.id = pc.user_id where pc.post_id = #{post_id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.avatar", column = "avatar"),
    })
    public List<PostComment> getComments(int postId);

    @Insert("insert into post_comments(user_id,post_id,parent_id,content,comment_time) values(#{user.id},#{post.id},#{parent.id},#{content},#{commentTime});")
    public void addComment(PostComment comment);
}
