package fly.frontend.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserMessage {
    private int id;
    private String type;
    private User sender;
    private User receiver;
    private String content;
    private Timestamp createdAt;
}
