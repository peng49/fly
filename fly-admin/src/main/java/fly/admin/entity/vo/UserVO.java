package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String avatar;
    private String name;
    private String city;
    private int experience;
    private int postCount;
    private int commentCount;
    private String signature;
    private String createdAt;
    private String updatedAt;
    private int isAdmin;
}
