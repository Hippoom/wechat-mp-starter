package org.restbucks.wechat.bff.http

import me.chanjar.weixin.mp.bean.result.WxMpUserFixture
import org.junit.Test

import static org.mockito.BDDMockito.given
import static org.restbucks.wechat.bff.http.WeChatUserRequestPostProcessor.weChatUser
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WeChatUserRestControllerTest extends AbstractWebMvcTest {

    @Test
    void returns_wechat_user_profile() {

        def userProfile = new WxMpUserFixture().build()

        given(wxMpUserService.userInfo(userProfile.openId)).willReturn(userProfile)

        // @formatter:off
        this.mockMvc.perform(
                    get("/rel/wechat/user/profile/me")
                    .with(weChatUser().withOpenId(userProfile.openId)).with(csrf().asHeader())
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
