package fly.frontend.service;

import fly.frontend.entity.model.OauthAccount;

import java.io.UnsupportedEncodingException;

public interface OauthService {
    String getRedirectUrl() throws UnsupportedEncodingException;

    OauthAccount get(String code);
}
