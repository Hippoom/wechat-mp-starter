package com.github.hippoom.wechat.mp.examples.oauth2.http

import me.chanjar.weixin.mp.api.WxMpMessageRouter
import me.chanjar.weixin.mp.api.WxMpService
import me.chanjar.weixin.mp.api.WxMpUserService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

@RunWith(SpringRunner)
@WebMvcTest(controllers = [
        HttpMvcConfig,
        HttpSecurityConfig,
        WeChatMpHttpConfigurationForTest]
)
@ActiveProfiles("test")
abstract class AbstractWebMvcTest {

    @Autowired
    protected MockMvc mockMvc

    @MockBean
    protected WxMpService wxMpService

    @MockBean
    protected WxMpUserService wxMpUserService

    @MockBean
    protected WxMpMessageRouter wxMpMessageRouter
}
