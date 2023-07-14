package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;


@Data
@Builder
@TableName(value = "post_comments")
public class PostComment {
    private Long id;
    private int level;
    private Long userId;
    private Long postId;
    private Long parentId;
    private String content;
    private int agreeCount;
    private LocalDateTime createdAt;

    @Tolerate
    public PostComment() {
    }
}
