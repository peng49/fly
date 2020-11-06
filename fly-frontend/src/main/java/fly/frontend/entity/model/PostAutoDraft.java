package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
@TableName("post_auto_draft")
public class PostAutoDraft {
    private Long id;
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updateAt;

    @Tolerate
    public PostAutoDraft(){}
}
