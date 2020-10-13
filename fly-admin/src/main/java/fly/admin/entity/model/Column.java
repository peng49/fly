package fly.admin.entity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "columns")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer sort;
}
