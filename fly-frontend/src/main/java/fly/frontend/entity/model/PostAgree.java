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
@TableName(value = "post_agree")
public class PostAgree {
    private Long id;

    private Long userId;

    private Long postId;

    private LocalDateTime createdAt;
}
