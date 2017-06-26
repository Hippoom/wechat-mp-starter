package org.restbucks.wechat.bff.http;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.github.hippoom.wechat.mp.web.bind.annotation.CurrentWeChatMpUser;
import com.github.hippoom.wechat.oauth.OpenId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.restbucks.wechat.bff.http.assembler.WeChatUserProfileResourceAssembler;
import org.restbucks.wechat.bff.http.resource.WeChatUserProfileResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeChatUserRestController {

    @NonNull
    private final WeChatUserProfileResourceAssembler userResourceAssembler;

    @NonNull
    private final WxMpUserService wxMpUserService;

    @SneakyThrows
    @RequestMapping(value = "/rel/wechat/user/profile/me", method = GET)
    public WeChatUserProfileResource me(@CurrentWeChatMpUser OpenId openId) {
        WxMpUser userProfile = wxMpUserService.userInfo(openId.getValue());
        return userResourceAssembler.toResource(userProfile);
    }


}
