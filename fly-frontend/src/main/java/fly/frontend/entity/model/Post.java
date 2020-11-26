package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

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
    private LocalDateTime publishAt;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private Integer essence;
    private Integer top;

    @Tolerate
    public Post() {
    }
}
