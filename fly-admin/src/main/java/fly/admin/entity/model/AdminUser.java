package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin_users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String avatar;

    private String rememberToken;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
