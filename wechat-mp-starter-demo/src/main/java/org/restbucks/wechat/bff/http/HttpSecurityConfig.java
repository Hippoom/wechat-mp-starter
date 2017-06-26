package org.restbucks.wechat.bff.http;

import com.github.hippoom.wechat.mp.autoconfigure.security.web.WeChatMpWebSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig extends WeChatMpWebSecurityConfigurerAdapter {


}
