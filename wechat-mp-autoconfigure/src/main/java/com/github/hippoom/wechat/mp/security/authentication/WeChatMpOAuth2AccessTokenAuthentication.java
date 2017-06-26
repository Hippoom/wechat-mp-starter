package com.github.hippoom.wechat.mp.security.authentication;

import com.github.hippoom.wechat.oauth.OpenId;
import java.util.Collection;
import java.util.Collections;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class WeChatMpOAuth2AccessTokenAuthentication implements Authentication {

    private final WxMpOAuth2AccessToken accessToken;

    public WeChatMpOAuth2AccessTokenAuthentication(WxMpOAuth2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return getOpenId();
    }

    @Override
    public Object getPrincipal() {
        return getOpenId();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return accessToken.getOpenId();
    }

    public OpenId getOpenId() {
        return OpenId.valueOf(accessToken.getOpenId());
    }
}
