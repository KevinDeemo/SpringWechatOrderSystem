package com.kevindeemo.service;

import com.kevindeemo.dto.OrderDTO;

public interface PushMessageService {
    /*
    * 订单状态变更消息
    * */
    void orderStatus(OrderDTO orderDTO);
}
