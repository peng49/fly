package fly.frontend.entity.model;

import lombok.Data;

@Data
public class OauthAccount {
    private int id;
    private String platform;
    private String openid;
    private User user;
}