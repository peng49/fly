package fly.web.entity.from;

import lombok.Data;

@Data
public class UpdateUserInfoFrom {
    private String email;
    private String username;
    private String city;
    private String signature;
}
