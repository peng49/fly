package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "navigations")
public class Navigation {
    private Long id;
    private String title;
    private String url;
    private Integer sort = 0;
    private Integer parentId = 0;
    private int status;
}
