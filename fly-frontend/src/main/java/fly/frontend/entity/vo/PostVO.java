package fly.frontend.entity.vo;

import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostVO {
    private int id;
    private Column column;
    private User author;
    private String title;
    private String originalContent;
    private String content;
    private int viewCount;
    private int replyCount;
    private int status;
    private Timestamp publishAt;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private int essence;
    private int top;
}
