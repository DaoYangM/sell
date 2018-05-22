package com.ye.sell.controller.portal;

import com.ye.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    private WxMpService wxMpService;

    @Autowired
    public void setWeChatMpConfig(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String test = "http://72stz9.natappfree.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(test, WxConsts.OAuth2Scope.SNSAPI_BASE,
                URIUtil.encodeURIComponent(returnUrl));

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();

            return "redirect:" + returnUrl +"?openid=" + openId;
        } catch (WxErrorException e) {
            log.error("【微信网页授权】 wxMpOAuth2AccessToken error");
            throw new SellException(e.getError().getErrorCode(), e.getError().getErrorMsg());
        }
    }
}
