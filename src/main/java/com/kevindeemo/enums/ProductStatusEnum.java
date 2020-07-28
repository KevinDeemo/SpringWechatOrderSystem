package com.kevindeemo.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 * 商品状态
 * */
@Getter
public enum ProductStatusEnum {
    UP(0, " 上架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
