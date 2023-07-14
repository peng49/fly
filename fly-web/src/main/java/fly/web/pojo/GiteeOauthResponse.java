package fly.web.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String scope;

    @JsonProperty("created_at")
    private int createdAt;
}
