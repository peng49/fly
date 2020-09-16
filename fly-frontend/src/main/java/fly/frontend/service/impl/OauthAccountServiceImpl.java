package fly.frontend.service.impl;

import fly.frontend.entity.po.OauthAccount;
import fly.frontend.mapper.OauthAccountMapper;
import fly.frontend.service.OauthAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OauthAccountServiceImpl implements OauthAccountService {

    @Resource
    private OauthAccountMapper oauthAccountMapper;

    @Override
    public OauthAccount add(OauthAccount account) {
        int row = oauthAccountMapper.add(account);
        if (row == 0) {
            throw new RuntimeException("新增账户失败");
        }
        return account;
    }

    @Override
    public OauthAccount get(String openid, String platform) {
        return oauthAccountMapper.get(openid, platform);
    }
}
