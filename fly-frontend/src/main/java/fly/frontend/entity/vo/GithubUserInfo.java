package fly.frontend.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
