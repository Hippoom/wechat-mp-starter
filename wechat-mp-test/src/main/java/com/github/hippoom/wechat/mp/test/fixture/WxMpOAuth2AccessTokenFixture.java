package com.github.hippoom.wechat.mp.test.fixture;

import com.github.hippoom.wechat.oauth.OpenId;
import java.util.UUID;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * Test fixture for {@link WxMpOAuth2AccessTokenFixture}.
 *
 * <pre class="code">
 *
 * import static com.github.hippoom.wechat.mp.test.fixture
 * .WxMpOAuth2AccessTokenFixture.aWxMpOAuth2AccessToken;
 *
 * //Declare OpenId openId = ...
 *
 * WxMpOAuth2AccessToken accessToken = aWxMpOAuth2AccessToken().with(openId).build();
 *
 * </pre>
 *
 * @see WxMpOAuth2AccessTokenFixture
 * @since 0.3.0
 */
public class WxMpOAuth2AccessTokenFixture {

    private WxMpOAuth2AccessToken target = new WxMpOAuth2AccessToken();

    private WxMpOAuth2AccessTokenFixture() {
        target.setOpenId(OpenId.valueOf(UUID.randomUUID().toString()).getValue());
        target.setAccessToken("accessToken");
        target.setRefreshToken("refreshToken");
        target.setExpiresIn(3600);
        target.setScope("snsapi_base");
    }

    /**
     * Customize the {@link OpenId}.
     *
     * @param openId a custom {@link OpenId}
     * @return this {@link WxMpOAuth2AccessTokenFixture}
     */
    public WxMpOAuth2AccessTokenFixture with(OpenId openId) {
        target.setOpenId(openId.getValue());
        return this;
    }

    /**
     * Build the {@link WxMpOAuth2AccessToken}.
     *
     * @return this {@link WxMpOAuth2AccessToken}
     */
    public WxMpOAuth2AccessToken build() {
        return target;
    }

    /**
     * Static factory method for {@link WxMpOAuth2AccessTokenFixture}.
     *
     * @return a {@link WxMpOAuth2AccessTokenFixture} with default values
     */
    public static WxMpOAuth2AccessTokenFixture aWxMpOAuth2AccessToken() {
        return new WxMpOAuth2AccessTokenFixture();
    }
}
