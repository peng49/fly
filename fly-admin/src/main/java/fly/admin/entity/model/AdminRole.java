package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "admin_roles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String slug;

    private Timestamp createdAt;

    private Timestamp updatedAt;

//    @ManyToMany(targetEntity = AdminPermission.class, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "admin_role_permissions",
//            //joinColumns,当前对象在中间表中的外键
//            joinColumns={@JoinColumn(name = "role_id",referencedColumnName = "id")},
//            //inverseJoinColumns，对方对象在中间表的外键
//            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")}
//    )
//    private List<AdminPermission> adminPermissions;
}
