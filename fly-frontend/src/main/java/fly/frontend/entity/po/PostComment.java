package fly.frontend.entity.po;

import lombok.Data;

import java.sql.Date;


@Data
public class PostComment {
    private int id;
    private int level;
    private User user;
    private Post post;
    private PostComment parent;
    private String content;
    private Date commentTime;
    private int agreeCount;
}
