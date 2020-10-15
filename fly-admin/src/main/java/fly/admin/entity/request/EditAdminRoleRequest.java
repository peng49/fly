package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditAdminRoleRequest {
    private String name;

    private String slug;
}
