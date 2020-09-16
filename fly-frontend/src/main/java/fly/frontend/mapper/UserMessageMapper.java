package fly.frontend.mapper;

import fly.frontend.entity.po.User;
import fly.frontend.entity.po.UserMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMessageMapper {
    @Insert("insert into user_message (receiver_id,sender_id,type,content,create_time) values (#{receiver.id},#{sender.id},#{type},#{content},#{createdAt,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(UserMessage message);


    @Select("select um.*,sender.avatar as sender_avatar,sender.username as sender_username,rec.username as rec_username,rec.avatar as rec_avatar from user_message as um " +
            "left join users as sender on sender.id = um.sender_id " +
            "left join users as rec on rec.id = um.receiver_id " +
            "where um.receiver_id = #{id} order by um.id desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "create_time"),
            @Result(property = "receiver.id", column = "receiver_id"),
            @Result(property = "receiver.username", column = "rec_username"),
            @Result(property = "receiver.avatar", column = "rec_avatar"),
            @Result(property = "sender.id", column = "sender_id"),
            @Result(property = "sender.avatar", column = "sender_avatar"),
            @Result(property = "sender.username", column = "sender_username"),
    })
    List<UserMessage> getMessagesForUser(User user);

    @Delete("delete from user_message where receiver_id = #{id}")
    void deleteByUser(User user);

    @Delete("delete from user_message where id = #{id}")
    void delete(UserMessage message);

    @Select("select * from user_message where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "type",column = "type"),
            @Result(property = "content",column = "content"),
            @Result(property = "sender.id",column = "sender_id"),
            @Result(property = "receiver.id",column = "receiver_id")
    })
    UserMessage get(int id);
}
