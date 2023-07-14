package fly.web.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

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

    private long publishCount;

    private long commentCount;

    private String registerAt;
    private List<String> bindPlatform;
    private int isAdmin;
}
