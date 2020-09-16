package fly.frontend.mapper;

import fly.frontend.entity.po.Post;
import fly.frontend.entity.po.PostComment;
import fly.frontend.pojo.PostFilterCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select p.*,u.username,u.avatar,c.name as column_name from posts as p inner join users as u on u.id = p.author_id inner join columns as c on c.id = p.column_id where p.column_id = #{columnId} and p.status = 1")
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
    public List<Post> findByColumnId(int columnId);

    @Select("select * from posts as p where p.author_id = #{id} order by p.id desc")
    public List<Post> findAllByAuthorId(int id);

    @Select("select * from posts as p where p.author_id = #{id} and p.status = 1 order by p.id desc")
    List<Post> findAllPublishByAuthorId(int id);

    @Select("select p.*,u.username,u.avatar,c.name as column_name from posts as p inner join users as u on u.id = p.author_id inner join columns as c on c.id = p.column_id where p.top = 1 limit #{limit}")
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
    public List<Post> findTop(int limit);

    @Select("select p.*,u.username,u.avatar,c.name as column_name from posts as p inner join users as u on u.id = p.author_id inner join columns as c on c.id = p.column_id where p.id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "column.id", column = "column_id"),
            @Result(property = "column.name", column = "column_name"),
            @Result(property = "title", column = "title"),
            @Result(property = "originalContent", column = "original_content"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "replyCount", column = "reply_count"),
            @Result(property = "status", column = "status"),
            @Result(property = "publishAt", column = "publish_at"),
            @Result(property = "author.id", column = "author_id"),
            @Result(property = "author.username", column = "username"),
            @Result(property = "author.avatar", column = "avatar")
    })
    public Post get(int id);


    @Insert("insert into posts(column_id,author_id,title,original_content,content,status,created_at,update_at,publish_at,reply_count,view_count,heat) values (#{column.id},#{author.id},#{title},#{originalContent},#{content},#{status},#{createdAt,jdbcType=TIMESTAMP},#{updateAt,jdbcType=TIMESTAMP},#{publishAt,jdbcType=TIMESTAMP},0,0,#{heat})")
    public void create(Post post);

    public void update(Post post);

    @Update("update posts set heat = #{heat} where id = #{id}")
    public void updateHeat(Post post);

    @Select("select pc.*,u.username,u.avatar from post_comments as pc inner join users u on u.id = pc.user_id where pc.post_id = #{post_id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "agreeCount", column = "agree_count"),
            @Result(property = "user.id", column = "user_id"),
            @Result(property = "user.username", column = "username"),
            @Result(property = "user.avatar", column = "avatar"),
            @Result(property = "parent.id", column = "parent_id")
    })
    List<PostComment> getComments(int postId);





    @Select("select * from (select p.*,count(0) as week_comment_total from posts as p left join post_comments as c on c.post_id = p.id where c.comment_time > date_sub(curdate(), interval 7 day) group by p.id) as temp order by week_comment_total desc limit #{limit}")
    List<Post> getEveryWeekCommentMax(int limit);

    @Update("update posts set top = 1 where id = #{id}")
    void top(Post post);

    @Update("update posts set essence = 1 where id = #{id}")
    void essence(Post post);

    List<Post> getByCondition(PostFilterCondition condition);

    @Update("update posts set reply_count = reply_count + 1 where id = #{postId}")
    public void replyCountInc(int postId);

    @Update("update posts set view_count = view_count + 1 where id = #{postId}")
    void viewCountInc(int postId);

    @Update("update posts set column_id = #{column.id}, title = #{title}, content = #{content}, status = #{status},publish_at = #{publishAt,jdbcType=TIMESTAMP}, original_content = #{originalContent} where id = #{id} ")
    void edit(Post post);
}
