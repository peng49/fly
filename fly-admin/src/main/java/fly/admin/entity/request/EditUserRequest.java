package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditUserRequest {
    private String username;
    private String email;
    private String name;
    private String password;
    private String avatar;
    private String city;
    private String signature;
}
