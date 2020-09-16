package fly.frontend.service;

import fly.frontend.entity.po.OauthAccount;

public interface OauthService {
    OauthAccount get(String code);
}
