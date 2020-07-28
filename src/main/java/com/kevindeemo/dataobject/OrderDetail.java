package com.kevindeemo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

//    商品名称
    private String productName;

//    当前价格,单位分
    private BigDecimal productPrice;

//    数量
    private Integer productQuantity;

//    小图
    private String productIcon;
}
