package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "columns")
public class Column {
    private int id;
    private String name;
    private int sort;
    private int status;
}
