package com.kevindeemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kevindeemo.dataobject.OrderDetail;
import com.kevindeemo.enums.OrderStatusEnum;
import com.kevindeemo.enums.PayStatusEnum;
import com.kevindeemo.utils.EnumUtil;
import com.kevindeemo.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
// 当variable为null时不返回
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    //    买家Id
    private String orderId;

    //    买家名字
    private String buyerName;

    //    买家电话
    private String buyerPhone;

    //    买家地址
    private String buyerAddress;

    //    买家微信openid
    private String buyerOpenId;

    //    订单总金额
    private BigDecimal orderAmount;

    //    订单状态, 默认为0新下单
    private Integer orderStatus;

    //    支付状态, 默认0未支付
    private Integer payStatus;

    //    创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //    更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

//    如果list为null，则不返回
//    需要跟前端约定具体的参数返回形式，也可以是返回一个空的list（只需要给orderDetailList初始化即可）
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
