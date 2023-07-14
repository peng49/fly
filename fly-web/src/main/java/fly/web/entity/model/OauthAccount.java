package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("oauth_account")
public class OauthAccount {
    private Long id;
    private String platform;
    private String openid;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
