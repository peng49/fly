package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditAdminUserRequest {
    private String username;

    private String password;

    private String name;

    private String avatar;
}
