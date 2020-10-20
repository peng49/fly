package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class AdminUserVO {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String avatar;

    private String rememberToken;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
