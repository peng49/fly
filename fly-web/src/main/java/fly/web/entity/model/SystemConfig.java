package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "system_config")
public class SystemConfig {
    @TableId(type = IdType.AUTO)
    private int id;

    private String attribute;

    private String value;

    private String remark;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
