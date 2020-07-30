package com.kevindeemo.controller;

import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("wechat")
public class WeChatController {

    @Autowired
    WxMpService wxMpService;

    @GetMapping("/authorize")
//    returnUrl : http://sell/com/#/ 编码后的形势，所以需要解码
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws IOException {
//        手动调试方式
//        log.info("进入auth方法......");
//        log.info("code = {}",code);
//
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22fc3eca01cead25&secret=e652f1e520ee23d41925e1b9ddc3843f&code="+ code +"&grant_type=authorization_code";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response={}", response);

//        SDK调试方式
//        1. 配置
//        2. 调用方法
        String url = "http://kevinsell.nat300.top/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
