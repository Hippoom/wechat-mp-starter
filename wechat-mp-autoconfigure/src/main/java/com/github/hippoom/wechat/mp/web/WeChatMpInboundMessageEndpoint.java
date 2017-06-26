package com.github.hippoom.wechat.mp.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/wechat/mp/webhooks/messaging")
public class WeChatMpInboundMessageEndpoint {

    @NonNull
    private final WxMpService wxMpService;

    @NonNull
    private final WxMpMessageRouter wxMpMessageRouter;

    @RequestMapping(method = GET)
    protected String handleAuthentication(@RequestParam String signature,
        @RequestParam String echostr,
        @RequestParam String timestamp,
        @RequestParam String nonce, HttpServletRequest request) {
        log.debug("receiving {}", request.getParameterMap());

        // see http://admin.wechat.com/wiki/index.php?title=Message_Authentication
        return wxMpService.checkSignature(timestamp, nonce, signature) ? echostr
            : "invalid authentication request";

    }

    @RequestMapping(method = POST)
    protected WxMpXmlOutMessage handleInboundMessage(@RequestBody String message) {
        log.debug("receiving {}", message);
        return wxMpMessageRouter.route(WxMpXmlMessage.fromXml(message));
    }

}
