package com.ye.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WeChatMpConfig {

    private WeChatAccountConfig weChatAccountConfig;

    @Autowired
    public void setWeChatAccountConfig(WeChatAccountConfig weChatAccountConfig) {
        this.weChatAccountConfig = weChatAccountConfig;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();

        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());

        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());

        return wxMpConfigStorage;
    }
}
