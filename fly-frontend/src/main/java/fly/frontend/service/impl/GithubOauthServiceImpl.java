package fly.frontend.service.impl;

import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.GithubOauthToken;
import fly.frontend.entity.vo.GithubUserInfo;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Objects;

@Service("GithubOauthServiceImpl")
public class GithubOauthServiceImpl implements OauthService {
    public static final String PLATFORM = "github";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OauthAccountService oauthAccountService;

    @Resource
    private UserService userService;

    @Resource
    private SystemConfigServiceImpl systemConfigService;

    @Override
    public String getRedirectUrl() throws UnsupportedEncodingException {
        String clientId = systemConfigService.getValue("github_oauth_client_id");
        String redirectUri = systemConfigService.getValue("github_oauth_redirect_uri");

        return String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=user", clientId, URLEncoder.encode(redirectUri, "UTF-8"));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public OauthAccount get(String code) {
        String clientId = systemConfigService.getValue("github_oauth_client_id");
        String redirectUri = systemConfigService.getValue("github_oauth_redirect_uri");
        String clientSecret = systemConfigService.getValue("github_oauth_client_secret");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("redirect_uri", redirectUri);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Accept", "application/json");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(parameters, authHeaders);

        ResponseEntity<GithubOauthToken> response = restTemplate.postForEntity("https://github.com/login/oauth/access_token", httpEntity, GithubOauthToken.class);
        if (!response.hasBody()) {
            throw new RuntimeException("获取token失败");
        }

        GithubOauthToken oauthToken = response.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + Objects.requireNonNull(oauthToken).getAccessToken());
        org.springframework.http.HttpEntity<Object> entity = new org.springframework.http.HttpEntity<>(null, headers);

        ResponseEntity<GithubUserInfo> responseEntity = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, entity, GithubUserInfo.class);

        GithubUserInfo userInfo = responseEntity.getBody();

        if(userInfo == null){
            throw new RuntimeException("获取token失败");
        }

        OauthAccount oauthAccount = oauthAccountService.getPlatformAccount(PLATFORM,userInfo.getOpenid());

        if (oauthAccount == null) {
            User user =  (User) SecurityUtils.getSubject().getPrincipal();
            if (user == null) {//如果是已登录状态，直接绑定gitee账号，如果未登录,新建账号
                user = new User();
                user.setUsername(userService.getUniqueUsername(userInfo.getLogin()));
                user.setAvatar(userInfo.getAvatarUrl());
                user.setUpdatedAt(LocalDateTime.now());
                user.setCreatedAt(LocalDateTime.now());
                userService.save(user);
            }

            OauthAccount account = new OauthAccount();
            account.setOpenid(userInfo.getOpenid());
            account.setPlatform(PLATFORM);
            account.setUserId(user.getId());
            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());
            oauthAccountService.save(account);

            oauthAccount = account;
        }
        return oauthAccount;
    }
}
