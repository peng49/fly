package fly.frontend.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserVO {
    private int id;
    private String username;
    private String email;
    private String avatar;
    private String city;
    private int experience;
    private String signature;
    private Timestamp createTime;
    private String[] bindPlatform;
    private int isAdmin;
}
