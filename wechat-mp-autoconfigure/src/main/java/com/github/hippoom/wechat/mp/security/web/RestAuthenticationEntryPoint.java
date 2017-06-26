package com.github.hippoom.wechat.mp.security.web;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RestAuthenticationEntryPoint
    implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource
        // without supplying any credentials
        // We should just send a 401 Unauthorized response
        // because there is no 'login page' to redirect to
        response.sendError(SC_UNAUTHORIZED, "Unauthorized");
    }
}
