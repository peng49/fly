package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_categories")
public class UserCategory {
    private Long id;
    private Long userId;
    private String name;
    private Integer enable = 1;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
