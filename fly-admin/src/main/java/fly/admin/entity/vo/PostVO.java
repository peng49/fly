package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostVO {
    private Integer id;

    private int columnId;
    private int authorId;
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
}
