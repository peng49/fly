package fly.frontend.entity.po;

import lombok.Data;

@Data
public class PostCommentAgree {
    private int id;
    private User user;
    private PostComment postComment;
}
