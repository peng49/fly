package fly.frontend.entity.vo;

import fly.frontend.entity.model.Column;
import fly.frontend.entity.model.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class PostVO {
    private Long id;
    private Column column;
    private User author;
    private String title;
    private String originalContent;
    private String content;
    private Integer viewCount;
    private Integer replyCount;
    private Integer status;
    private LocalDateTime publishAt;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private Integer essence;
    private Integer top;

    /**
     * 是否收藏
     */
    private boolean collected;

    private Integer collectedCount;
    /**
     * 是否推荐
     */
    private boolean recommended;

    private Integer recommendedCount;
}
