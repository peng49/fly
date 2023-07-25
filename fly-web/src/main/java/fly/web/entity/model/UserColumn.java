package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user_columns")
public class UserColumn {
    private Long id;
    private Long userId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
