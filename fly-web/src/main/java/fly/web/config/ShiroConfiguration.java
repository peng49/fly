package fly.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.web.entity.model.User;
import fly.web.service.UserService;
import fly.web.shiro.OauthToken;
import fly.web.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Configuration
@Slf4j
public class ShiroConfiguration {

    @Resource
    private UserService userService;

    private UserFilter getUserFilter() {
        class Inner extends UserFilter {
            @Override
            protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
                HttpServletRequest req = (HttpServletRequest) request;
                if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"))) {
                    // 返回json
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(new ObjectMapper().writeValueAsString(HttpUtils.fail("未登录或者登录已失效")));
                } else {
                    //重定向
                    StringBuilder url = new StringBuilder();
                    url.append(req.getScheme())
                            .append("://")
                            .append(req.getServerName())
                            .append(":")
                            .append(req.getServerPort())
                            .append(req.getServletPath());
                    if (req.getQueryString() != null) {
                        url.append("?").append(req.getQueryString());
                    }
                    String loginUrl = this.getLoginUrl();
                    WebUtils.issueRedirect(request, response, loginUrl + "?redirect=" + URLEncoder.encode(url.toString(), "UTF-8"));
                }
            }
        }
        return new Inner();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/user/login");


        // 设置filter 返回json数据
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", getUserFilter());
        factoryBean.setFilters(filters);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/static/**", "anon"); //静态文件可匿名访问
        map.put("/", "anon"); //首页同上
        map.put("/column/*", "anon"); //列表同上
        map.put("/u/*", "anon"); //个人主页
        map.put("/user/register", "anon"); //注册
        map.put("/user/forget", "anon"); //忘记密码
        map.put("/post/detail/*", "anon"); //详情同上
        map.put("/oauth/**", "anon");
        map.put("/error/**", "anon");

        map.put("/user/logout", "logout");
        map.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    private AuthorizingRealm getAuthorizingRealm() {
        /**
         * 设置一个用户认证的 realm
         */
        AuthorizingRealm authorizingRealm = new AuthorizingRealm() {
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
                log.info(authenticationToken.toString());
                if (authenticationToken.getPrincipal() == null) {
                    log.info("======>" + authenticationToken.toString());
                    return null;
                }
                // 获取用户信息
                String username = authenticationToken.getPrincipal().toString();
                log.info(username);

                User user = userService.getByUsername(username);
                if (user == null) {
                    // 这里返回后会报出对应异常
                    throw new UnknownAccountException("用户不存在");
                }
                // 这里验证authenticationToken和simpleAuthenticationInfo的信息
                return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
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

        return authorizingRealm;
    }


    private AuthorizingRealm getOauthRealm() {
        class OauthRealm extends AuthorizingRealm {
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                return null;
            }

            /**
             * 认证
             * @param authenticationToken
             * @return
             * @throws AuthenticationException
             */
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                if (authenticationToken.getPrincipal() == null) {
                    return null;
                }

                User user = (User) authenticationToken.getPrincipal();

                return new SimpleAuthenticationInfo(user, user.getId(), getName());
            }
        }

        OauthRealm oauthRealm = new OauthRealm();
        oauthRealm.setAuthenticationTokenClass(OauthToken.class);
        return oauthRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        AuthorizingRealm authorizingRealm = getAuthorizingRealm();
        Collection<Realm> realms = new ArrayList<>(2);
        realms.add(authorizingRealm);
        realms.add(getOauthRealm());

        securityManager.setRealms(realms);

        return securityManager;
    }

}
