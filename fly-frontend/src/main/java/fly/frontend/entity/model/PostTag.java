package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tags")
public class PostTag {
    private Long id;
    private Long postId;
    private Long tagId;
    private LocalDateTime createdAt;
}
