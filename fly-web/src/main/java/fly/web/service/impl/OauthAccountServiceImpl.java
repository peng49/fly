package fly.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fly.web.dao.OauthAccountMapper;
import fly.web.entity.model.OauthAccount;
import fly.web.service.OauthAccountService;
import org.springframework.stereotype.Service;

@Service
public class OauthAccountServiceImpl extends ServiceImpl<OauthAccountMapper, OauthAccount> implements OauthAccountService {

    @Override
    public OauthAccount getPlatformAccount(String platform, String openid) {
        return getOne(
                Wrappers.lambdaQuery(OauthAccount.class)
                        .eq(OauthAccount::getPlatform, platform)
                        .eq(OauthAccount::getOpenid, openid)
        );
    }
}
