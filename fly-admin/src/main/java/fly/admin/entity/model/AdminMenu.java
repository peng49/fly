package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin_menu")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int parentId;

    private int sort;

    private String title;

    private String icon;

    private String uri;

    private String permission;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
