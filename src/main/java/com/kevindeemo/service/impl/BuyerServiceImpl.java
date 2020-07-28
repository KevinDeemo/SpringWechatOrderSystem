package com.kevindeemo.service.impl;

import com.kevindeemo.dto.OrderDTO;
import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.exception.SellException;
import com.kevindeemo.service.BuyerService;
import com.kevindeemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO==null){
            log.error("【取消订单】查不到该订单，orderI={}", orderId);;
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        if (StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            log.error("【查询订单列表】openid或者orderid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null) return null;
//        判断是否是自己的订单
        if (!orderDTO.getOrderId().equals(orderId)){
            log.error("【查询订单】订单的openid不一致，openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
