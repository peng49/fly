package fly.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.web.entity.model.OauthAccount;

public interface OauthAccountService extends IService<OauthAccount> {
    OauthAccount getPlatformAccount(String platform, String openid);
}
