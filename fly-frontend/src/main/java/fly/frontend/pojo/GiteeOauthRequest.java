package fly.frontend.pojo;

import lombok.Data;

@Data
public class GiteeOauthRequest {
    private String grant_type;
    private String code;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
}
