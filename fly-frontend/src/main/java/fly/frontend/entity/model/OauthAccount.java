package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oauth_account")
public class OauthAccount {
    private Long id;
    private String platform;
    private String openid;
    private User user;
}
