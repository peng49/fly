package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.sql.Date;
import java.sql.Timestamp;


@Data
@Builder
@TableName(value = "post_comments")
public class PostComment {
    private int id;
    private int level;
    private int userId;
    private int postId;
    private int parentId;
    private String content;
    private int agreeCount;
    private Timestamp createdAt;

    @Tolerate
    public PostComment() {
    }
}
