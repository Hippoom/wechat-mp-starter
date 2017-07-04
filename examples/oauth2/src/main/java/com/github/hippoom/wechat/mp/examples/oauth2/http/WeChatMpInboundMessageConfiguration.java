package com.github.hippoom.wechat.mp.examples.oauth2.http;

import com.github.hippoom.wechat.mp.autoconfigure.messaging.WeChatMpInboundMessagingConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WeChatMpInboundMessageConfiguration extends WeChatMpInboundMessagingConfigurerAdapter {


    @Override
    protected void configure(WxMpMessageRouter router) {
        // empty router
    }
}
