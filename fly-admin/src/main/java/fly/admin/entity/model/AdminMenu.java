package fly.admin.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;


import java.time.LocalDateTime;

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

    private String component;

    private String permission;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
