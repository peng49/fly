package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_blacklist")
public class UserBlacklist {
    private Long id;
    private Long userId;
    private Long blackUserId;
    private LocalDateTime createdAt;
}
