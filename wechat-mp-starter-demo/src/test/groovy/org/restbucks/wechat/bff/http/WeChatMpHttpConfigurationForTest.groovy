package org.restbucks.wechat.bff.http

import com.github.hippoom.wechat.mp.autoconfigure.web.WeChatMpWebConfiguration
import com.github.hippoom.wechat.mp.autoconfigure.web.WeChatMpWebMethodConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([
        WeChatMpWebConfiguration,
        WeChatMpWebMethodConfiguration
])
class WeChatMpHttpConfigurationForTest {

}
