package org.restbucks.wechat.bff.wechat.messaging;

import static java.lang.String.format;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
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
public class QrCodeScannedEventHandler implements WxMpMessageHandler {

    private final WxMpKefuService kefuService;

    @Setter
    private String publicBaseUri = "https://wechat.restbucks.org";

    @SneakyThrows
    public void handle(String eventKey, String fromUser) {
        String storeNumber = getStore(eventKey);
        kefuService.sendKefuMessage(
            WxMpKefuMessage.NEWS().addArticle(assembleArticle(storeNumber)).toUser(fromUser)
                .build());
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
        WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        handle(wxMessage.getEventKey(), wxMessage.getFromUser());

        return null;
    }

    private WxArticle assembleArticle(String storeNumber) {
        WxArticle article = new WxArticle();
        article.setTitle(format("Welcome to Restbucks Store %s", storeNumber));
        article.setUrl(publicBaseUri + "/index.html#/place-order-form/" + storeNumber);
        article.setPicUrl("https://avatars3.githubusercontent.com/u/18325423?v=3&s=200");
        return article;
    }

    private String getStore(String eventKey) {
        return Optional.ofNullable(eventKey) // "qrscene_store_123"
            .map(k -> k.substring(k.indexOf("_") + 1)) // getting "store_123"
            .map(k -> k.substring(k.indexOf("_") + 1)) // getting "123"
            .orElseThrow(() -> new IllegalArgumentException(
                "Cannot extract store number due to empty qr code key"));
    }
}
