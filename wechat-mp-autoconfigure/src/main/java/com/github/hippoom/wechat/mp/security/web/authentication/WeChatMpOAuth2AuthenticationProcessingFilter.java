package com.github.hippoom.wechat.mp.security.web.authentication;

import com.github.hippoom.wechat.mp.security.authentication.WeChatMpOAuth2AccessTokenAuthentication;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.SneakyThrows;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class WeChatMpOAuth2AuthenticationProcessingFilter
    extends AbstractAuthenticationProcessingFilter {

    @Setter
    private WxMpService wxMpService;

    public WeChatMpOAuth2AuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public WeChatMpOAuth2AuthenticationProcessingFilter(
        RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {

        final String code = request.getParameter("code");

        WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);

        return new WeChatMpOAuth2AccessTokenAuthentication(accessToken);
    }
}
