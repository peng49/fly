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
@TableName("post_comment_agree")
public class PostCommentAgree {
    private Long id;
    private Long userId;
    private Long postId;
    private Long postCommentId;
    private LocalDateTime createdAt;
}
