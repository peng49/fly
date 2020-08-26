package fly.frontend.pojo;

import lombok.Data;

@Data
public class UpdateUserInfo {
    private String email;
    private String username;
    private String city;
    private String signature;
}
