package fly.frontend.mapper;

import fly.frontend.entity.User;
import fly.frontend.entity.UserMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMessageMapper{
    @Insert("insert into user_message (receiver_id,sender_id,type,content,create_time) values (#{receiver.id},#{sender.id},#{type},#{content},#{createdAt,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(UserMessage message);


    @Select("select * from user_message as um where um.receiver_id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "content", column = "content"),
            @Result(property = "createdAt", column = "create_time"),
            @Result(property = "receiver.id", column = "receiver_id"),
            @Result(property = "sender.id", column = "sender"),
    })
    List<UserMessage> getMessagesForUser(User user);
}
