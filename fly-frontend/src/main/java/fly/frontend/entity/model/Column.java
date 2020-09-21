package fly.frontend.entity.model;

import lombok.*;
import lombok.experimental.Tolerate;

@Data
@Builder
public class Column {
    private int id;
    private String name;
    private int sort;

    @Tolerate
    Column(){}
}
