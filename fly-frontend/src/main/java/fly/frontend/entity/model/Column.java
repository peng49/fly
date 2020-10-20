package fly.frontend.entity.model;

import lombok.*;
import lombok.experimental.Tolerate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Column {
    private int id;
    private String name;
    private int sort;
    private int status;
}
