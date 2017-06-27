package me.chanjar.weixin.mp.bean.result

import com.github.hippoom.wechat.oauth.OpenId

class WxMpOAuth2AccessTokenFixture {

    private WxMpOAuth2AccessToken target

    WxMpOAuth2AccessTokenFixture() {
        target = new WxMpOAuth2AccessToken()
        target.setOpenId(OpenId.valueOf(UUID.randomUUID().toString()).value)
        target.setAccessToken("accessToken")
        target.setRefreshToken("refreshToken")
        target.setExpiresIn(3600)
        target.setScope("snsapi_base")
    }

    WxMpOAuth2AccessTokenFixture with(OpenId openId) {
        target.setOpenId(openId.value)
        this
    }

    WxMpOAuth2AccessToken build() {
        return target
    }
}
