package com.kevindeemo.service.impl;

import com.kevindeemo.config.WechatAccountConfig;
import com.kevindeemo.dto.OrderDTO;
import com.kevindeemo.service.PushMessageService;
import com.kevindeemo.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.api.impl.WxMpTemplateMsgServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    @Qualifier("wxMpService")
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));

//        因为是测试账号所以暂时写死
        templateMessage.setToUser("ot6LCvl5XSpq2dbv-eiCnUo-x8U0");
//        templateMessage.setToUser(orderDTO.getBuyerOpenId());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","15951872121"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5","￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临")
        );
        templateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
//            这个异常不需要抛出去，因为会导致订单事务性回滚（消息推送不是关键业务，日志记录即可）
            log.error("【微信模板消息】发送失败, {}", e);
        }
    }
}
