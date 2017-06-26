package com.github.hippoom.wechat.mp.autoconfigure.messaging;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.context.annotation.Bean;

public abstract class WeChatMpInboundMessagingConfigurerAdapter {

    @Bean
    protected WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        configure(router);
        return router;
    }

    protected void configure(WxMpMessageRouter router) {
    }
}
