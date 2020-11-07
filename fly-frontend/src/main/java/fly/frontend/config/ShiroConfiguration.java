package fly.frontend.config;

import fly.frontend.entity.model.User;
import fly.frontend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

@Configuration
@Slf4j
public class ShiroConfiguration {

    @Resource
    private UserService userService;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/user/login");

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/static/**","anon"); //静态文件可匿名访问
        map.put("/","anon"); //首页同上
        map.put("/column/*","anon"); //列表同上
        map.put("/post/detail/*","anon"); //详情同上

        map.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        /**
         * 设置一个用户认证的 realm
         */
        AuthorizingRealm authorizingRealm = new AuthorizingRealm(){
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
                log.info(authenticationToken.toString());
                if (authenticationToken.getPrincipal() == null) {
                    return null;
                }
                // 获取用户信息
                String username = authenticationToken.getPrincipal().toString();
                log.info(username);
                User user = userService.getByUsername(username);
                log.info(user.toString());
                if (user == null) {
                    // 这里返回后会报出对应异常
                    return null;
                } else {
                    // 这里验证authenticationToken和simpleAuthenticationInfo的信息
                    return new SimpleAuthenticationInfo(username,user.getPassword(), getName());
                }
            }

            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                return null;
            }
        };

        //配置 BCrypt
        authorizingRealm.setCredentialsMatcher((authenticationToken, authenticationInfo) -> {
            UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
            //要验证的明文密码
            String plaintext = new String(userToken.getPassword());

            //数据库中的加密后的密文
            String hashed = authenticationInfo.getCredentials().toString();
            return BCrypt.checkpw(plaintext, hashed);
        });

        securityManager.setRealm(authorizingRealm);

        return securityManager;
    }

}
