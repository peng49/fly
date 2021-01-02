package fly.frontend.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVO {
    private String code;

    private String message;

    private Object data;
}
