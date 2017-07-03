package org.restbucks.wechat.bff.wechat.messaging;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextInboundMessageRepeater implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
        WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        return WxMpXmlOutMessage.TEXT()
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser())
            .content(wxMessage.getContent())
            .build();
    }
}
