package me.chanjar.weixin.mp.bean.result

import com.github.hippoom.wechat.oauth.OpenId

class WxMpUserFixture {
    private WxMpUser target = new WxMpUser()

    WxMpUserFixture() {
        target.setOpenId(OpenId.valueOf(UUID.randomUUID().toString()).value)
        target.setNickname("John Doe")
        target.setHeadImgUrl("https://avatar.com/johndoe")
    }

    WxMpUserFixture with(OpenId openId) {
        target.setOpenId(openId.value)
        this
    }

    WxMpUser build() {
        target
    }
}
