package fly.frontend.service;

import fly.frontend.entity.OauthAccount;

public interface OauthService {
    OauthAccount get(String code);
}
