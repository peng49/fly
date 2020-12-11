package fly.frontend.entity.vo;

import fly.frontend.entity.dto.PostDTO;
import fly.frontend.entity.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCommentVO {
    private Long id;
    private int level;
    private User user;
    private PostDTO post;
    private Long parentId;
    private String content;
    private int agreeCount;
    private boolean userIsAgree;
    private String createdAt;
}
