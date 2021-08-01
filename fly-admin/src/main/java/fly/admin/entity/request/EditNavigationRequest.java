package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditNavigationRequest {
    private String title;
    private String url;
    private Integer sort = 0;
    private Integer parentId = 0;
    private Integer status = 1;
}
