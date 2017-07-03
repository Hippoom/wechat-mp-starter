package com.github.hippoom.wechat.mp.test.fixture;

import com.github.hippoom.wechat.oauth.OpenId;
import java.util.UUID;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * Test fixture for {@link WxMpUser}.
 *
 * <pre class="code">
 *
 * import static com.github.hippoom.wechat.mp.test.fixture.WxMpUserFixture.aWxMpUser;
 *
 * //Declare OpenId openId = ...
 *
 * WxMpUser wxMpUser = aWxMpUser().with(openId).build();
 *
 * </pre>
 *
 * @see WxMpUser
 * @since 0.3.0
 */
public class WxMpUserFixture {

    private WxMpUser target = new WxMpUser();

    private WxMpUserFixture() {
        target.setOpenId(OpenId.valueOf(UUID.randomUUID().toString()).getValue());
        target.setNickname("John Doe");
        target.setHeadImgUrl("https://avatar.com/johndoe");
    }

    /**
     * Customize the {@link OpenId}.
     *
     * @param openId a custom {@link OpenId}
     * @return this {@link WxMpUserFixture}
     */
    public WxMpUserFixture with(OpenId openId) {
        target.setOpenId(openId.getValue());
        return this;
    }

    /**
     * Build the {@link WxMpUser}.
     *
     * @return {@link WxMpUser}
     */
    public WxMpUser build() {
        return target;
    }

    /**
     * Static factory method for {@link WxMpUserFixture}.
     *
     * @return a {@link WxMpUserFixture} with default values
     */
    public static WxMpUserFixture aWxMpUser() {
        return new WxMpUserFixture();
    }
}
