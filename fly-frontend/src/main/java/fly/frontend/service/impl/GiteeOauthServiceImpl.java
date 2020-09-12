package fly.frontend.service.impl;

import fly.frontend.entity.OauthAccount;
import fly.frontend.service.OauthService;
import org.springframework.stereotype.Service;

@Service
public class GiteeOauthServiceImpl implements OauthService {
    @Override
    public OauthAccount get(String code) {
        System.out.println(code);
        return null;
    }
}
