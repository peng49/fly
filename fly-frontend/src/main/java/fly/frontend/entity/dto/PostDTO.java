package fly.frontend.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private Long id;
    private String title;
}
