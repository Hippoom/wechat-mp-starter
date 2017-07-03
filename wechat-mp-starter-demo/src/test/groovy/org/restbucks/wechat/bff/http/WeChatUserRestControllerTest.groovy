package org.restbucks.wechat.bff.http

import org.junit.Test

import static com.github.hippoom.wechat.mp.test.fixture.WxMpUserFixture.aWxMpUser
import static com.github.hippoom.wechat.mp.test.web.servlet.request.WxMpOAuth2AccessTokenRequestPostProcessor.aWeChatMpUser
import static org.mockito.BDDMockito.given
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WeChatUserRestControllerTest extends AbstractWebMvcTest {

    @Test
    void returns_wechat_user_profile() {

        def userProfile = aWxMpUser().build()

        given(wxMpUserService.userInfo(userProfile.openId)).willReturn(userProfile)

        // @formatter:off
        this.mockMvc.perform(
                    get("/rel/wechat/user/profile/me")
                    .with(aWeChatMpUser().withOpenId(userProfile.openId)).with(csrf().asHeader())
                )
                .andDo(print())
	            .andExpect(status().isOk())
                .andExpect(jsonPath("openId").doesNotExist())// hide internal state
                .andExpect(jsonPath("nickname").exists())
                .andExpect(jsonPath("avatar").exists())
                .andExpect(jsonPath("_links.self.href").exists())
        // @formatter:on
    }
}
