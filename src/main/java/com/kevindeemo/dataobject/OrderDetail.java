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

    /*
    * JPA 会根据驼峰式命名规则对名字进行转换，然后到数据库中找对应的数据
    * eg: detailId -> detail_id
    * 所以在命名的时候需要考虑匹配因素
    * */
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
