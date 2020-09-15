package fly.frontend.service;

import fly.frontend.entity.OauthAccount;
import fly.frontend.entity.User;

public interface OauthAccountService {
    OauthAccount add(OauthAccount account);

    OauthAccount get(String openid, String platform);
}
