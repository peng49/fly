package fly.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListResultVO {
    private List<?> items;
    private Integer page;
    private Integer pageSize;
    private Long total;
}
