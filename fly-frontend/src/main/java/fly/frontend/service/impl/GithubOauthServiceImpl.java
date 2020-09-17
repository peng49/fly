package fly.frontend.service.impl;

import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.GithubOauthToken;
import fly.frontend.entity.vo.GithubUserInfo;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

@Service("GithubOauthServiceImpl")
public class GithubOauthServiceImpl implements OauthService {
    public static final String PLATFORM = "github";

    @Value("${oauth.github.client-id}")
    private String clientId;

    @Value("${oauth.github.client-secret}")
    private String clientSecret;

    @Value("${oauth.github.redirect-uri}")
    private String redirectUri;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private OauthAccountService oauthAccountService;

    @Resource
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Override
    public String getRedirectUrl() throws UnsupportedEncodingException {
        return String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=user", clientId, URLEncoder.encode(redirectUri,"UTF-8"));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public OauthAccount get(String code) {
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

        OauthAccount oauthAccount = oauthAccountService.get(Objects.requireNonNull(userInfo).getOpenid(), PLATFORM);
        if (oauthAccount == null) {
            User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
            if (user == null) {//如果是已登录状态，直接绑定gitee账号，如果未登录,新建账号
                user = new User();
                user.setUsername(userInfo.getLogin() + "_" + RandomStringUtils.randomAlphabetic(10));
                user.setAvatar(userInfo.getAvatarUrl());
                userService.add(user);
            }
            OauthAccount account = new OauthAccount();
            account.setOpenid(userInfo.getOpenid());
            account.setPlatform(PLATFORM);
            account.setUser(user);
            oauthAccount = oauthAccountService.add(account);
        }
        return oauthAccount;
    }
}