package fly.frontend.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GiteeUserInfo {
    private int id;
    private String login;
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
}
