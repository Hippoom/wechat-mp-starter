package com.github.hippoom.wechat.mp.examples.oauth2.http.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatUserProfileResource extends ResourceSupport {

    private String nickname;
    private String avatar;
}
