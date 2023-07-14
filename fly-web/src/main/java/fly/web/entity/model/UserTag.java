package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_tags")
public class UserTag {
    private Long id;
    private Long tagId;
    private Long userId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
