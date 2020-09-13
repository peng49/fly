package fly.frontend.service.impl;

import fly.frontend.entity.OauthAccount;
import fly.frontend.entity.User;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import org.springframework.stereotype.Service;

@Service
public class OauthAccountServiceImpl implements OauthAccountService {
    @Override
    public OauthAccount add(OauthAccount account) {
        return null;
    }

    @Override
    public OauthAccount get(String openid) {
        return null;
    }

    @Override
    public User getUser(OauthAccount account) {
        return null;
    }
}
