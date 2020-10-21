package fly.admin.entity.vo;

import fly.admin.entity.model.AdminPermission;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class AdminRoleVO {
    private Integer id;

    private String name;

    private String slug;

    private List<AdminPermission> permissions;

    private String createdAt;

    private String updatedAt;
}
