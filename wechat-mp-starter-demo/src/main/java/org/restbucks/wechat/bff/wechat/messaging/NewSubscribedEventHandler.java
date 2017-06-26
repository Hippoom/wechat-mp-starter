package org.restbucks.wechat.bff.wechat.messaging;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@RequiredArgsConstructor
public class NewSubscribedEventHandler implements WxMpMessageHandler {

    private final WxMpKefuService kefuService;

    @Setter
    private String publicBaseUri = "https://wechat.restbucks.org";

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
        WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        kefuService.sendKefuMessage(
            WxMpKefuMessage.NEWS().addArticle(assembleArticle())
                .toUser(wxMessage.getFromUser())
                .build());
        return null;
    }

    private WxArticle assembleArticle() {
        WxArticle article = new WxArticle();
        article.setTitle("Welcome to Restbucks");
        article.setUrl(publicBaseUri + "/index.html");
        article.setPicUrl("https://avatars3.githubusercontent.com/u/18325423?v=3&s=200");
        return article;
    }
}
