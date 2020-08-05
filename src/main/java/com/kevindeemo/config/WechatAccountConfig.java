package com.kevindeemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /*
     * 公众平台appId
     * */
    private String mpAppId;

    /*
     * 公众平台appSecret
     * */
    private String mpAppSecret;

    /*
    * 卖家扫码登陆开放平台appId
    * */
    private String openAppId;

    /*
    * 卖家扫码登陆开放平台AppSecret
    * */
    private String openAppSecret;

    /*
     * 商户号
     * */
    private String mchId;

    /*
     * 商户密钥
     * */
    private String mchKey;

    /*
     * 商户证书路径
     * */
    private String keyPath;

    /*
    * 微信支付异步通知
    * */
    private String notifyUrl;

    /*
    * 授权appId
    * */
    private String myAuthorizeAppId;

    /*
    * 授权appSecret
    * */
    private String myAuthorizeAppSecret;

    /*
    * 微信模板id
    * */
    private Map<String, String> templateId;
}
