package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Tolerate;

import java.sql.Timestamp;

@Data
@Builder
@TableName(value = "posts")
public class Post {
    private Long id;
    private Integer columnId;
    private Long authorId;
    private String title;
    private String originalContent;
    private String content;
    private Integer viewCount;
    private Integer replyCount;
    private Double heat;
    private Integer status;
    private Timestamp publishAt;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Integer essence;
    private Integer top;

    @Tolerate
    public Post() {
    }
}
