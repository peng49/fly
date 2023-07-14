package fly.web.shiro;

import fly.web.entity.model.User;
import org.apache.shiro.authc.AuthenticationToken;

public class OauthToken implements AuthenticationToken {
    private User user;

    public OauthToken(User user){
        this.user = user;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return this.user.getId();
    }
}
