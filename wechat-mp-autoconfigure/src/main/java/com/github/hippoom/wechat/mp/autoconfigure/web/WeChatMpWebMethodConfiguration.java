package com.github.hippoom.wechat.mp.autoconfigure.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.github.hippoom.wechat.mp.web.method.support.OpenIdHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
public class WeChatMpWebMethodConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new OpenIdHandlerMethodArgumentResolver());
    }

}
