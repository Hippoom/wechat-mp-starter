package com.github.hippoom.wechat.mp.autoconfigure;

import com.github.hippoom.wechat.mp.autoconfigure.web.WeChatMpWebConfiguration;
import com.github.hippoom.wechat.mp.autoconfigure.web.WeChatMpWebMethodConfiguration;
import me.chanjar.weixin.mp.api.WxMpCardService;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpDataCubeService;
import me.chanjar.weixin.mp.api.WxMpDeviceService;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpMaterialService;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpStoreService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.WxMpUserBlacklistService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Provide default {@link WxMpService}.
 *
 * @see WeChatMpProperties
 * @see WeChatMpWebConfiguration
 * @see WeChatMpWebMethodConfiguration
 * @since 0.1.0
 */
@Configuration
@EnableConfigurationProperties(WeChatMpProperties.class)
@Import(
    {
        WeChatMpWebConfiguration.class,
        WeChatMpWebMethodConfiguration.class
    }
)
public class WeChatMpConfiguration {

    /**
     * Default in memory {@link WxMpConfigStorage}, you can provide a custom instance, see <a
     * href="https://github.com/Hippoom/wechat-mp-starter/wiki/Autowiring-WxMpConfigStorage">wiki</a>
     * for detail.
     *
     * @param weChatMpProperties see {@link WeChatMpProperties}
     * @return see {@link WxMpConfigStorage} and {@link WxMpInMemoryConfigStorage}
     *
     * @see WxMpConfigStorage
     * @see WxMpInMemoryConfigStorage
     * @see WeChatMpProperties
     */
    @Bean
    @ConditionalOnMissingBean
    protected WxMpConfigStorage configStorage(WeChatMpProperties weChatMpProperties) {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(weChatMpProperties.getAppId());
        configStorage.setSecret(weChatMpProperties.getAppSecret());
        configStorage.setToken(weChatMpProperties.getToken());
        configStorage.setAesKey(weChatMpProperties.getAesKey());
        return configStorage;
    }

    /**
     * Default {@link WxMpService}, you can provide a custom instance, see <a
     * href="https://github.com/Hippoom/wechat-mp-starter/wiki/Autowiring-WxMpService">wiki</a>
     * for detail.
     *
     * @param wxMpConfigStorage {@link WxMpConfigStorage}
     * @return {@link WxMpService}
     *
     * @see WxMpService
     */
    @Bean
    @ConditionalOnMissingBean
    protected WxMpService wxMpService(WxMpConfigStorage wxMpConfigStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }

    /**
     * Default {@link WxMpKefuService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpKefuService}
     *
     * @see WxMpKefuService
     */
    @Bean
    protected WxMpKefuService wxMpKefuService(WxMpService wxMpService) {
        return wxMpService.getKefuService();
    }

    /**
     * Default {@link WxMpUserService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpUserService}
     *
     * @see WxMpUserService
     */
    @Bean
    protected WxMpUserService wxMpUserService(WxMpService wxMpService) {
        return wxMpService.getUserService();
    }

    /**
     * Default {@link WxMpUserTagService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpUserTagService}
     *
     * @see WxMpUserTagService
     */
    @Bean
    protected WxMpUserTagService wxMpUserTagService(WxMpService wxMpService) {
        return wxMpService.getUserTagService();
    }

    /**
     * Default {@link WxMpMenuService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpMenuService}
     *
     * @see WxMpMenuService
     */
    @Bean
    protected WxMpMenuService wxMpMenuService(WxMpService wxMpService) {
        return wxMpService.getMenuService();
    }

    /**
     * Default {@link WxMpMaterialService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpMaterialService}
     *
     * @see WxMpMaterialService
     */
    @Bean
    protected WxMpMaterialService wxMpMaterialService(WxMpService wxMpService) {
        return wxMpService.getMaterialService();
    }

    /**
     * Default {@link WxMpQrcodeService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpQrcodeService}
     *
     * @see WxMpQrcodeService
     */
    @Bean
    protected WxMpQrcodeService wxMpQrcodeService(WxMpService wxMpService) {
        return wxMpService.getQrcodeService();
    }

    /**
     * Default {@link WxMpTemplateMsgService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpTemplateMsgService}
     *
     * @see WxMpTemplateMsgService
     */
    @Bean
    protected WxMpTemplateMsgService wxMpTemplateMsgService(WxMpService wxMpService) {
        return wxMpService.getTemplateMsgService();
    }

    /**
     * Default {@link WxMpUserBlacklistService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpUserBlacklistService}
     *
     * @see WxMpUserBlacklistService
     */
    @Bean
    protected WxMpUserBlacklistService wxMpUserBlacklistService(WxMpService wxMpService) {
        return wxMpService.getBlackListService();
    }

    /**
     * Default {@link WxMpCardService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpCardService}
     *
     * @see WxMpCardService
     */
    @Bean
    protected WxMpCardService wxMpCardService(WxMpService wxMpService) {
        return wxMpService.getCardService();
    }

    /**
     * Default {@link WxMpDeviceService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpDeviceService}
     *
     * @see WxMpDeviceService
     */
    @Bean
    protected WxMpDeviceService wxMpDeviceService(WxMpService wxMpService) {
        return wxMpService.getDeviceService();
    }

    /**
     * Default {@link WxMpDataCubeService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpDataCubeService}
     *
     * @see WxMpDataCubeService
     */
    @Bean
    protected WxMpDataCubeService wxMpDataCubeService(WxMpService wxMpService) {
        return wxMpService.getDataCubeService();
    }

    /**
     * Default {@link WxMpStoreService} provider.
     *
     * @param wxMpService {@link WxMpService}
     * @return {@link WxMpStoreService}
     *
     * @see WxMpStoreService
     */
    @Bean
    protected WxMpStoreService wxMpStoreService(WxMpService wxMpService) {
        return wxMpService.getStoreService();
    }
}
