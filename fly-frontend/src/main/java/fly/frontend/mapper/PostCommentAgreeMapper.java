package fly.frontend.mapper;

import fly.frontend.entity.PostCommentAgree;
import fly.frontend.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostCommentAgreeMapper {
    @Insert("insert into post_comment_agree(user_id,post_id,comment_id,created_at) values(#{user.id},0,#{postComment.id},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(PostCommentAgree postCommentAgree);

    @Select("select count(0) from post_comment_agree where user_id = #{user.id} and comment_id = #{commentId}")
    boolean isExisted(User user, int commentId);

    @Delete("delete from post_comment_agree where user_id = #{user.id} and comment_id = #{commentId}")
    void delete(User user, int commentId);
}
