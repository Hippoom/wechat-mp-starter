package com.github.hippoom.wechat.mp.examples.oauth2.http.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.github.hippoom.wechat.mp.examples.oauth2.http.WeChatUserRestController;
import com.github.hippoom.wechat.mp.examples.oauth2.http.resource.WeChatUserProfileResource;
import com.github.hippoom.wechat.oauth.OpenId;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class WeChatUserProfileResourceAssembler extends
    ResourceAssemblerSupport<WxMpUser, WeChatUserProfileResource> {


    public WeChatUserProfileResourceAssembler() {
        super(WeChatUserRestController.class, WeChatUserProfileResource.class);
    }

    @Override
    public WeChatUserProfileResource toResource(WxMpUser entity) {
        WeChatUserProfileResource resource = new WeChatUserProfileResource();
        resource.setAvatar(entity.getHeadImgUrl());
        resource.setNickname(entity.getNickname());
        resource
            .add(
                linkTo(methodOn(WeChatUserRestController.class)
                    .me(OpenId.valueOf(entity.getOpenId()))).withSelfRel());
        return resource;
    }
}
