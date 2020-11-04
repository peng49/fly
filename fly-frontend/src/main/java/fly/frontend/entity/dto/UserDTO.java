package fly.frontend.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private int id;
    private String username;
    private String avatar;
}
