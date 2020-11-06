package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private int sort;
    private int status;
}
