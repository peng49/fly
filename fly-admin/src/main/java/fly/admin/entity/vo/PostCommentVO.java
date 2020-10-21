package fly.admin.entity.vo;

import fly.admin.entity.model.Post;
import fly.admin.entity.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCommentVO {
    private int id;
    private int level;
    private User user;
    private Post post;
    private String content;
    private String commentTime;
    private int agreeCount;
}
