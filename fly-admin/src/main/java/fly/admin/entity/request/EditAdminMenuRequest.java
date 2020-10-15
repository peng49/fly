package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditAdminMenuRequest {
    private int parentId;

    private int sort;

    private String title;

    private String icon;

    private String uri;

    private String permission;
}
