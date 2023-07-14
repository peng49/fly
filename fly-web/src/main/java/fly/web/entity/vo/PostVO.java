package fly.web.entity.vo;

import fly.web.entity.model.Column;
import fly.web.entity.model.User;
import lombok.Builder;
import lombok.Data;

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
    private String publishAt;
    private String createdAt;
    private String updateAt;
    private Integer essence;
    private Integer top;

    /**
     * 是否收藏
     */
    private boolean collected;

    private long collectedCount;

    /**
     * 是否推荐
     */
    private boolean agree;

    private long agreeCount;
}
