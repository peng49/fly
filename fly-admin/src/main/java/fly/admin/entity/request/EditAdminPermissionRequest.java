package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditAdminPermissionRequest {
    private int parentId;

    private String name;

    private String slug;

    private String httpMethod;

    private String httpPath;
}
