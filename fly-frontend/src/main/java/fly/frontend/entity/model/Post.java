package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
@TableName(value = "posts")
public class Post {
    private int id;
    private Integer columnId;
    private Integer authorId;
    private String title;
    private String originalContent;
    private String content;
    private int viewCount;
    private int replyCount;
    private double heat;
    private int status;
    private Timestamp publishAt;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private int essence;
    private int top;

    @Tolerate
    public Post() {
    }
}
