package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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
}
