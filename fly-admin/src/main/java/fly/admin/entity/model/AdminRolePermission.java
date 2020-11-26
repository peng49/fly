package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_role_permissions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminRolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "permission_id")
    private int permissionId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
