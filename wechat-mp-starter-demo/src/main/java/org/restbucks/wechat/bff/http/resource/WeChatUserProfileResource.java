package org.restbucks.wechat.bff.http.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatUserProfileResource extends ResourceSupport {

    private String nickname;
    private String avatar;
}
