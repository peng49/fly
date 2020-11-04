package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
@TableName("user_posts")
public class UserPost {
    private Long id;
    private Long userId;
    private Long postId;
    private Timestamp createdAt;

    @Tolerate
    public UserPost() {
    }
}
