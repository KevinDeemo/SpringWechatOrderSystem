package com.kevindeemo.dataobject;

/*
* 订单主表
* */

import com.kevindeemo.enums.OrderStatusEnum;
import com.kevindeemo.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

//     买家Id
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

//    支付状态, 默认0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

//    创建时间
    private Date createTime;

//    更新时间
    private Date updateTime;

//    @Transient
//    private List<OrderDetail> orderDetailList;
}
