package fly.frontend.entity;

import lombok.Data;

@Data
public class PostCommentAgree {
    private int id;
    private User user;
    private PostComment postComment;
}
