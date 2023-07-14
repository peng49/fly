package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("post_auto_draft")
public class PostAutoDraft {
    private Long id;
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @Tolerate
    public PostAutoDraft(){}
}
