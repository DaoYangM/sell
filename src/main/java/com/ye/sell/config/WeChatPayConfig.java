package com.ye.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatPayConfig {

    private WeChatAccountConfig weChatAccountConfig;

    @Autowired
    public void setWeChatAccountConfig(WeChatAccountConfig weChatAccountConfig) {
        this.weChatAccountConfig = weChatAccountConfig;
    }

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();

        bestPayService.setWxPayH5Config(wxPayH5Config());

        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {

        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weChatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(weChatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(weChatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(weChatAccountConfig.getMchKey());
        wxPayH5Config.setKeyPath(weChatAccountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(weChatAccountConfig.getNotifyUrl());

        return wxPayH5Config;
    }
}
