package com.github.hippoom.wechat.mp.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "wechat.mp")
public class WeChatMpProperties {

    private String appId = "yourAppId";
    private String appSecret = "yourAppSecret";
    private String token = "aRandomString";

}
