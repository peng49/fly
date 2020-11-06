package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("post_comment_agree")
public class PostCommentAgree {
    private Long id;
    private Long userId;
    private Long postCommentId;
}
