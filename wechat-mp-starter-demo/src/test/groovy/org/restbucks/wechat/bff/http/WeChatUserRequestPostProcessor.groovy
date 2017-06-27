package org.restbucks.wechat.bff.http

import com.github.hippoom.wechat.mp.security.authentication.WeChatMpOAuth2AccessTokenAuthentication
import com.github.hippoom.wechat.oauth.OpenId
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessTokenFixture
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.request.RequestPostProcessor

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication

class WeChatUserRequestPostProcessor implements RequestPostProcessor {

    private OpenId openId = OpenId.valueOf(UUID.randomUUID().toString())
    private String csrfToken = "csrfToken"


    WeChatUserRequestPostProcessor with(OpenId openId) {
        this.openId = openId
        this
    }

    WeChatUserRequestPostProcessor withOpenId(String openId) {
        with(OpenId.valueOf(openId))
    }

    WeChatUserRequestPostProcessor csrfToken(String csrfToken) {
        this.csrfToken = csrfToken
        this
    }

    @Override
    MockHttpServletRequest postProcessRequest(
            MockHttpServletRequest mockHttpServletRequest) {
        WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessTokenFixture()
                .with(openId).build()
        RequestPostProcessor delegate = authentication(new WeChatMpOAuth2AccessTokenAuthentication(accessToken))
        delegate.postProcessRequest(mockHttpServletRequest)
    }

    static weChatUser() {
        new WeChatUserRequestPostProcessor()
    }

}
