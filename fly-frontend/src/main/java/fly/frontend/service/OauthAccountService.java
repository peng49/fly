package fly.frontend.service;

import fly.frontend.entity.model.OauthAccount;

public interface OauthAccountService {
    OauthAccount add(OauthAccount account);

    OauthAccount get(String openid, String platform);
}
