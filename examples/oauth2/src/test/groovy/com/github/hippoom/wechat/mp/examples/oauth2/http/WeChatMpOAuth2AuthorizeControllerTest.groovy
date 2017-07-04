package com.github.hippoom.wechat.mp.examples.oauth2.http

import org.junit.Test

import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WeChatMpOAuth2AuthorizeControllerTest extends AbstractWebMvcTest {

    @Test
    void it_should_redirect_to_wechat_to_finish_oauth_protocol() throws Exception {

        String origin = "http://www.example.com/index.html?a=b#/route"
        String encodedOrigin = Base64.urlEncoder.encodeToString(origin.getBytes("UTF-8"))


        def redirectUri = "http://localhost:8080/wechat/oauth/token"

        given(wxMpService.oauth2buildAuthorizationUrl(redirectUri,
                "snsapi_base", encodedOrigin)).willReturn("https://open.weixin.qq.com")


        this.mockMvc.perform(get("/wechat/oauth/authorize")
                .param("origin", origin)) // it seems that the controller will decode the parameter automatically only for browser request
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://open.weixin.qq.com"))

    }
}