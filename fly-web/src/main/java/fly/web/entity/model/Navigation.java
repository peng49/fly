package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "navigations")
public class Navigation {
    private Long id;
    private String title;
    private String url;
    private Integer sort;
    private Integer parentId;
    private int status;
}
