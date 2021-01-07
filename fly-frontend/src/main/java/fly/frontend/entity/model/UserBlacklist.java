package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_blacklist")
public class UserBlacklist {
    private Long id;
    private Long userId;
    private Long blackUserId;
    private LocalDateTime createdAt;
}
