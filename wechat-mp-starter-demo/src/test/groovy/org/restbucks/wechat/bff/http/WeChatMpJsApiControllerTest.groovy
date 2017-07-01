package org.restbucks.wechat.bff.http

import me.chanjar.weixin.common.bean.WxJsapiSignature
import org.junit.Test

import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WeChatMpJsApiControllerTest extends AbstractWebMvcTest {

    @Test
    void it_should_redirect_to_wechat_to_finish_oauth_protocol() throws Exception {

        String url = "http://www.example.com/index.html?a=b#/route"

        WxJsapiSignature signature = expectedSignature(url)

        given(wxMpService.createJsapiSignature(url)).willReturn(signature)


        this.mockMvc.perform(get("/wechat/mp/js/config")
                .param("url", url)) // it seems that the controller will decode the parameter automatically only for browser request
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("appId").exists())
                .andExpect(jsonPath("timestamp").exists())
                .andExpect(jsonPath("nonceStr").exists())
                .andExpect(jsonPath("signature").exists())

    }

    private WxJsapiSignature expectedSignature(String url) {
        def signature = new WxJsapiSignature()
        signature.setUrl(url)
        signature.setAppId("appId")
        signature.setNonceStr("nonce")
        signature.setSignature("signature")
        signature.setTimestamp(1L)
        signature
    }
}