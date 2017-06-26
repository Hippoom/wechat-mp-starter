package com.github.hippoom.wechat.mp.web.method.support;

import com.github.hippoom.wechat.mp.security.authentication.WeChatMpOAuth2AccessTokenAuthentication;
import com.github.hippoom.wechat.mp.web.bind.annotation.CurrentWeChatMpUser;
import com.github.hippoom.wechat.oauth.OpenId;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class OpenIdHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(OpenId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
        ModelAndViewContainer modelAndViewContainer,
        NativeWebRequest nativeWebRequest,
        WebDataBinderFactory webDataBinderFactory) throws Exception {

        WeChatMpOAuth2AccessTokenAuthentication user = (WeChatMpOAuth2AccessTokenAuthentication)
            SecurityContextHolder.getContext().getAuthentication();

        CurrentWeChatMpUser weChatUser = methodParameter
            .getParameterAnnotation(CurrentWeChatMpUser.class);
        if (weChatUser == null) {
            return null;
        }

        return user.getPrincipal();
    }
}