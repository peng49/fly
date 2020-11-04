package fly.frontend.entity.vo;

import fly.frontend.entity.dto.PostDTO;
import fly.frontend.entity.model.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostCommentVO {
    private int id;
    private int level;
    private User user;
    private PostDTO post;
    private int parentId;
    private String content;
    private int agreeCount;
    private Timestamp createdAt;
}
