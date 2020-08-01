package com.kevindeemo.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(0, "商品不存在"),
    PRODUCT_STOCK__NOT_ENOUGH(1, "商品库存不足"),
    ORDER_NOT_EXIST(2, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(3, "订单详情不存在"),
    ORDER_STATUS_ERROR(4, "订单状态不正确"),
    ORDER_UPDATE_FAIL(5, "订单更新失败"),
    ORDER_DETAIL_EMPTY(6, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(7, "订单支付状态不正确"),
    PARAM_ERROR(8, "参数不正确"),
    CART_EMPTY(9, "购物车为空"),
    ORDER_OWNER_ERROR(10, "该订单不属于当前用户"),
    WX_MP_ERROR(11, "微信公众账号方面错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(12, "微信支付异步通知金额校验不通过"),
    CANCEL_SUCCESS(13, "订单取消成功"),
    FINISH_SUCCESS(14, "订单完结成功"),
    PRODUCT_ONSALE_SUCCESS(15, "商品上架成功"),
    PRODUCT_STATUS_ERROR(16,"商品状态不正确"),
    PRODUCT_OFFSALE_SUCCESS(17, "商品下架成功")
    ;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
