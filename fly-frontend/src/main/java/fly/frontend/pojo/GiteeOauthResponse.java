package fly.frontend.pojo;

import lombok.Data;

@Data
public class GiteeOauthResponse {
    /**
     * "access_token": "xxxxxxxxxxxxxxxx",
     * "token_type": "bearer",
     * "expires_in": 86400,
     * "refresh_token": "xxxxxxxxxxxxxxxxxxxxxxxxxx",
     * "scope": "user_info emails",
     * "created_at": 1599991747
     */
    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String refreshToken;
    private String scope;
    private String createdAt;
}
