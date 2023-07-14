package fly.web.service;

import fly.web.entity.model.OauthAccount;

import java.io.UnsupportedEncodingException;

public interface OauthService {
    String getRedirectUrl() throws UnsupportedEncodingException;

    OauthAccount get(String code);
}
