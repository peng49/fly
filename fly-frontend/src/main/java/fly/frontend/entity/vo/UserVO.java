package fly.frontend.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String avatar;
    private String city;
    private int experience;
    private String signature;

    private int publishCount;

    private int commentCount;

    private String registerAt;
    private String[] bindPlatform;
    private int isAdmin;
}
