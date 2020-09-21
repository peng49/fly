package fly.frontend.entity.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
public class PostAutoDraft {
    private int id;
    private Post post;
    private User user;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updateAt;

    @Tolerate
    public PostAutoDraft(){}
}
