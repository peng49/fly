package fly.frontend.mapper;

import fly.frontend.entity.UserMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMessageMapper{
    @Insert("insert into user_message (receiver_id,sender_id,type,content,create_time) values (#{receiver.id},#{sender.id},#{type},#{content},#{createdAt,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int create(UserMessage message);
}
