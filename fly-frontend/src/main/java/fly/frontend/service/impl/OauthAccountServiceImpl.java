package fly.frontend.service.impl;

import fly.frontend.entity.OauthAccount;
import fly.frontend.entity.User;
import fly.frontend.mapper.OauthAccountMapper;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OauthAccountServiceImpl implements OauthAccountService {

    @Resource
    private OauthAccountMapper oauthAccountMapper;

    @Override
    public OauthAccount add(OauthAccount account) {
        return oauthAccountMapper.add(account);
    }

    @Override
    public OauthAccount get(String openid) {
        return oauthAccountMapper.get(openid);
    }

    @Override
    public User getUser(OauthAccount account) {
        return null;
    }
}
