package com.github.hippoom.wechat.mp.examples.core.http;

import com.github.hippoom.wechat.mp.autoconfigure.messaging.WeChatMpInboundMessagingConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WeChatMpInboundMessageConfiguration extends WeChatMpInboundMessagingConfigurerAdapter {

    private final QrCodeScannedEventHandler qrCodeScannedEventHandler;
    private final DefaultInboundMessageHandler defaultInboundMessageHandler;
    private final TextInboundMessageRepeater textInboundMessageRepeater;

    @Override
    protected void configure(WxMpMessageRouter router) {
        // @formatter:off
        router
            .rule().async(false)
                .msgType(WxConsts.XML_MSG_TEXT)
                .handler(textInboundMessageRepeater)
            .end();

        router
            .rule()
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SCAN).event(WxConsts.EVT_SUBSCRIBE)
                .handler(qrCodeScannedEventHandler)
            .next();

        router
            .rule()
                .handler(defaultInboundMessageHandler)
            .end();
        // @formatter:on
    }
}
