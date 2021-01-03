package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("user_collection")
public class UserCollection {
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;

    @Tolerate
    public UserCollection() {
    }
}
