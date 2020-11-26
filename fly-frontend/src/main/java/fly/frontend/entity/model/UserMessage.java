package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_message")
public class UserMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String type;
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime createdAt;
}
