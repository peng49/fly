package fly.frontend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fly.frontend.entity.model.OauthAccount;

public interface OauthAccountService extends IService<OauthAccount> {
    OauthAccount getPlatformAccount(String platform, String openid);
}
