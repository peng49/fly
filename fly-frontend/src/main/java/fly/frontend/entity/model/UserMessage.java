package fly.frontend.entity.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserMessage {
    private int id;
    private String type;
    private int senderId;
    private int receiverId;
    private String content;
    private Timestamp createdAt;
}
