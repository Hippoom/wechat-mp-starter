package com.github.hippoom.wechat.mp.test.web.servlet.request;

import com.github.hippoom.wechat.mp.security.authentication.WeChatMpOAuth2AccessTokenAuthentication;
import com.github.hippoom.wechat.mp.test.fixture.WxMpOAuth2AccessTokenFixture;
import com.github.hippoom.wechat.oauth.OpenId;
import java.util.UUID;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

// @formatter:off
/**
 * Test fixture for {@link WxMpOAuth2AccessTokenFixture}.
 * <pre class="code">
 *
 * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
 * import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * import static com.github.hippoom.wechat.mp.test.web.servlet.request
 *     .WxMpOAuth2AccessTokenRequestPostProcessor.aWeChatMpUser;
 *
 * &#064;Test public void returns_wechat_user_profile() {
 *     this.mockMvc.perform(
 *         get("/rel/wechat/user/profile/me").with(aWeChatMpUser().withOpenId(userProfile.openId))
 *     ).andDo(print()).andExpect(status().isOk());
 * }
 *
 * </pre>
 *
 * @see RequestPostProcessor
 * @since 0.3.0
 */
//@formatter:on
public class WxMpOAuth2AccessTokenRequestPostProcessor implements RequestPostProcessor {

    private OpenId openId = OpenId.valueOf(UUID.randomUUID().toString());

    /**
     * Customize the {@link OpenId}.
     *
     * @param openId a custom {@link OpenId}
     * @return this {@link WxMpOAuth2AccessTokenRequestPostProcessor}
     */
    public WxMpOAuth2AccessTokenRequestPostProcessor with(OpenId openId) {
        this.openId = openId;
        return this;
    }

    /**
     * Customize the {@link OpenId}.
     *
     * @param openId a custom {@link OpenId} value
     * @return this {@link WxMpOAuth2AccessTokenRequestPostProcessor}
     */
    public WxMpOAuth2AccessTokenRequestPostProcessor withOpenId(String openId) {
        return with(OpenId.valueOf(openId));
    }

    @Override
    public MockHttpServletRequest postProcessRequest(
        MockHttpServletRequest mockHttpServletRequest) {
        WxMpOAuth2AccessToken accessToken = WxMpOAuth2AccessTokenFixture.aWxMpOAuth2AccessToken()
            .with(openId).build();
        RequestPostProcessor delegate =
            SecurityMockMvcRequestPostProcessors
                .authentication(new WeChatMpOAuth2AccessTokenAuthentication(accessToken));
        return delegate.postProcessRequest(mockHttpServletRequest);
    }

    /**
     * Static factory method for {@link WxMpOAuth2AccessTokenRequestPostProcessor}.
     *
     * @return a {@link WxMpOAuth2AccessTokenRequestPostProcessor} with default values
     */
    public static WxMpOAuth2AccessTokenRequestPostProcessor aWeChatMpUser() {
        return new WxMpOAuth2AccessTokenRequestPostProcessor();
    }

}
