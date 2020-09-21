package fly.frontend.entity.model;

import lombok.*;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
public class Post {
    private int id;
    private Column column;
    private User author;
    private String title;
    private String originalContent;
    private String content;
    private int viewCount;
    private int replyCount;
    private double heat;
    private int status;
    private Timestamp publishAt;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private int essence;
    private int top;

    @Tolerate
    Post(){}
}
