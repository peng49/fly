package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class AdminUserVO {
    private Integer id;

    private String username;

    private String name;

    private List<Integer> roleIds;

    private List<Integer> permissionIds;

    private String avatar;

    private String rememberToken;

    private String createdAt;

    private String updatedAt;
}
