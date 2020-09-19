package fly.frontend.entity.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostAutoDraft {
    private int id;
    private Post post;
    private User user;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updateAt;
}
