package com.github.hippoom.wechat.mp.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Built-in HTTP endpoint to get config to initiate WeChat MP JS SDK.
 *
 * @see <a href="http://admin.wechat.com/wiki/index.php?title=JS_SDK_DOCUMENT">wiki</a>
 * @since 0.2.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class WeChatMpJsApiController {

    @NonNull
    private final WxMpService weChatMpService;

    /**
     * see <a href="http://admin.wechat.com/wiki/index.php?title=JS_SDK_DOCUMENT
     * #Step_3:_Inject_Correct_Authentication_Configuration_via_the_config_API">
     * Inject Correct Authentication Configuration via the config API </a>.
     */
    @SneakyThrows
    @RequestMapping(value = "/wechat/mp/js/config", method = GET)
    protected WxJsapiSignature askForConfig(@RequestParam(name = "url") String url) {
        return weChatMpService.createJsapiSignature(url);
    }

}
