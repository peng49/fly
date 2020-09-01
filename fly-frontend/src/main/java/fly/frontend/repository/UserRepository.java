package fly.frontend.repository;

import lombok.Data;

@Data
public class UserRepository {
    private String username;
    private String email;
    private String avatar;
    private String signature;
}
