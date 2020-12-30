package fly.frontend.service.impl;

import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.model.User;
import fly.frontend.entity.vo.GiteeUserInfo;
import fly.frontend.pojo.GiteeOauthResponse;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service("GiteeOauthServiceImpl")
public class GiteeOauthServiceImpl implements OauthService {
    public static final String PLATFORM = "gitee";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OauthAccountService oauthAccountService;

    @Resource
    private SystemConfigServiceImpl systemConfigService;

    @Resource
    private UserService userService;

    @Override
    public String getRedirectUrl() throws UnsupportedEncodingException {
        String clientId = systemConfigService.getValue("gitee_oauth_client_id");
        String redirectUri = systemConfigService.getValue("gitee_oauth_redirect_uri");

        return String.format("https://gitee.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code", clientId, URLEncoder.encode(redirectUri,"UTF-8"));
    }

    @Override
    public OauthAccount get(String code) {
        String clientId = systemConfigService.getValue("gitee_oauth_client_id");
        String redirectUri = systemConfigService.getValue("gitee_oauth_redirect_uri");
        String clientSecret = systemConfigService.getValue("gitee_oauth_client_secret");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "authorization_code");
        parameters.add("code", code);
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("redirect_uri", redirectUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<GiteeOauthResponse> response = restTemplate.postForEntity("https://gitee.com/oauth/token", httpEntity, GiteeOauthResponse.class);
        if (!response.hasBody()) {
            log.info(code+" get token fail");
            throw new RuntimeException("获取token失败");
        }

        String accessToken = Objects.requireNonNull(response.getBody()).getAccessToken();

        //通过token获取用户信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        HttpEntity<Object> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<GiteeUserInfo> responseEntity = restTemplate.exchange(String.format("https://gitee.com/api/v5/user?access_token=%s", accessToken), HttpMethod.GET, request, GiteeUserInfo.class);

        GiteeUserInfo userInfo = responseEntity.getBody();

        String oauthId = Objects.requireNonNull(userInfo).getId();

        log.info(userInfo.toString());

        OauthAccount oauthAccount = oauthAccountService.getPlatformAccount(PLATFORM,oauthId);

        if (oauthAccount == null) {
            User user =  (User) SecurityUtils.getSubject().getPrincipal();
            if (user == null) {//如果是已登录状态，直接绑定gitee账号，如果未登录,新建账号
                user = new User();
                user.setUsername(userService.getUniqueUsername(userInfo.getName()));
                user.setAvatar(userInfo.getAvatarUrl());
                user.setUpdatedAt(LocalDateTime.now());
                user.setCreatedAt(LocalDateTime.now());
                userService.save(user);
            }
            OauthAccount account = new OauthAccount();
            account.setOpenid(oauthId);
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
