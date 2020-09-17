package fly.frontend.service.impl;

import fly.frontend.entity.OauthAccount;
import fly.frontend.entity.User;
import fly.frontend.pojo.GiteeOauthResponse;
import fly.frontend.entity.vo.GiteeUserInfo;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class GiteeOauthServiceImpl implements OauthService {
    public static final String PLATFORM = "gitee";

    @Value("${oauth.gitee.client-id}")
    private String clientId;

    @Value("${oauth.gitee.client-secret}")
    private String clientSecret;

    @Value("${oauth.gitee.redirect-uri}")
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
    public OauthAccount get(String code) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("code", code);
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("redirect_uri", redirectUri);
        System.out.println(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<GiteeOauthResponse> response = restTemplate.postForEntity("https://gitee.com/oauth/token", httpEntity, GiteeOauthResponse.class);
        if (!response.hasBody()) {
            throw new RuntimeException("获取token失败");
        }

        String accessToken = Objects.requireNonNull(response.getBody()).getAccessToken();
        System.out.println(accessToken);

        //通过token获取用户信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        HttpEntity<Object> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<GiteeUserInfo> responseEntity = restTemplate.exchange(String.format("https://gitee.com/api/v5/user?access_token=%s", accessToken), HttpMethod.GET, request, GiteeUserInfo.class);

        GiteeUserInfo userInfo = responseEntity.getBody();
        System.out.println(userInfo);

        int oauthId = Objects.requireNonNull(userInfo).getId();

        OauthAccount oauthAccount = oauthAccountService.get(String.valueOf(oauthId), PLATFORM);
        if (oauthAccount == null) {
            User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
            if (user == null) {//如果是已登录状态，直接绑定gitee账号，如果未登录,新建账号
                user = new User();
                user.setUsername(userInfo.getName() + "_" + RandomStringUtils.randomAlphabetic(10));
                user.setAvatar(userInfo.getAvatarUrl());
                userService.add(user);
            }
            OauthAccount account = new OauthAccount();
            account.setOpenid(String.valueOf(oauthId));
            account.setPlatform(PLATFORM);
            account.setUser(user);
            oauthAccount = oauthAccountService.add(account);
        }
        return oauthAccount;
    }
}
