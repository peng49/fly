package fly.frontend.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import fly.frontend.dao.UserMapper;
import fly.frontend.entity.model.OauthAccount;
import fly.frontend.entity.model.User;
import fly.frontend.entity.model.UserMessage;
import fly.frontend.pojo.GiteeOauthResponse;
import fly.frontend.entity.vo.GiteeUserInfo;
import fly.frontend.service.OauthAccountService;
import fly.frontend.service.OauthService;
import fly.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

@Service("GiteeOauthServiceImpl")
public class GiteeOauthServiceImpl implements OauthService {
    public static final String PLATFORM = "gitee";

    @Value("${oauth.gitee.client-id}")
    private String clientId;

    @Value("${oauth.gitee.client-secret}")
    private String clientSecret;

    @Value("${oauth.gitee.redirect-uri}")
    private String redirectUri;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private OauthAccountService oauthAccountService;

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpSession httpSession;

    @Override
    public String getRedirectUrl() throws UnsupportedEncodingException {
        return String.format("https://gitee.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code", clientId, URLEncoder.encode(redirectUri,"UTF-8"));
    }

    @Override
    public OauthAccount get(String code) {
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

        OauthAccount oauthAccount = oauthAccountService.getOne(
                Wrappers.<OauthAccount>lambdaQuery()
                        .eq(OauthAccount::getOpenid, oauthId)
                        .eq(OauthAccount::getPlatform, PLATFORM)
        );

        if (oauthAccount == null) {
            User user = (User) httpSession.getAttribute(UserService.LOGIN_KEY);
            if (user == null) {//如果是已登录状态，直接绑定gitee账号，如果未登录,新建账号
                user = new User();
                user.setUsername(userService.getUniqueUsername(userInfo.getName()));
                user.setAvatar(userInfo.getAvatarUrl());
                userMapper.insert(user);
            }
            OauthAccount account = new OauthAccount();
            account.setOpenid(String.valueOf(oauthId));
            account.setPlatform(PLATFORM);
            account.setUser(user);
            oauthAccountService.save(account);
            oauthAccount = account;
        }
        return oauthAccount;
    }
}
