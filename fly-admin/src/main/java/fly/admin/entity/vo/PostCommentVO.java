package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostCommentVO {
    private int id;
    private int level;
    private int userId;
    private int postId;
    private int parentId;
    private String content;
    private Timestamp commentTime;
    private int agreeCount;
}
