package fly.frontend.entity.model;

import lombok.Data;

@Data
public class PostCommentAgree {
    private int id;
    private User user;
    private PostComment postComment;
}
