package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user_message")
public class UserMessage {
    private Long id;
    private String type;
    private Long senderId;
    private Long receiverId;
    private String content;
    private Timestamp createdAt;
}
