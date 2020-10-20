package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginVO {
    private String username;
    private String token;
    private String avatar;
}
