package com.github.hippoom.wechat.mp.security.web.authentication;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class WeChatMpOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String origin = request.getParameter("state");

        URL raw = new URL(
            new String(Base64.getUrlDecoder().decode(origin.getBytes(Charset.forName("UTF-8"))),
                Charset.forName("UTF-8")));
        response.sendRedirect(raw.toString());
    }
}

