package fly.frontend.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {
    private int id;
    private String title;
}
