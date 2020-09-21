package fly.frontend.entity.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.sql.Date;


@Data
@Builder
public class PostComment {
    private int id;
    private int level;
    private User user;
    private Post post;
    private PostComment parent;
    private String content;
    private Date commentTime;
    private int agreeCount;

    @Tolerate
    PostComment(){}
}
