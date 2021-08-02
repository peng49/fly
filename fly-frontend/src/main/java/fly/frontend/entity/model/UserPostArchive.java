package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_post_archive")
public class UserPostArchive {
    private Long id;
    private Long userId;
    private Integer year;
    private Integer mouth;
    private Integer postCount;
}
