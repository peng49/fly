package fly.admin.entity.vo;

import fly.admin.entity.dto.PostDTO;
import fly.admin.entity.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCommentVO {
    private int id;
    private int level;
    private UserDTO user;
    private PostDTO post;
    private String content;
    private String commentTime;
    private int agreeCount;
}
