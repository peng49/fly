package fly.web.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubUserInfo {
    @JsonProperty("id")
    private String openid;

    @JsonProperty("login")
    private String login;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("avatar_url")
    private String avatarUrl;
}
