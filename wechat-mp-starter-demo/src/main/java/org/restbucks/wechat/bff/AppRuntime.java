package org.restbucks.wechat.bff;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppRuntime {

    private String publicBaseUri = "https://wechat.restbucks.org";

    public String getPublicUri(String relativePath) {
        return publicBaseUri + relativePath;
    }
}
