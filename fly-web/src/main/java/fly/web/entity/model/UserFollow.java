package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_follow")
public class UserFollow {
    private Long id;
    private Long userId;
    private Long followUserId;
    private LocalDateTime createdAt;
}
