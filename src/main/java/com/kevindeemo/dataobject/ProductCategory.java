package com.kevindeemo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
 * 类目表
 * product_category（Mysql里表名）idea里对应驼峰命名（省略下划线）
 * SpringBoot自动匹配
 * */
@Entity
// 动态更新操作时间
@DynamicUpdate
@Data
public class ProductCategory {

    //    类目id
    @Id
    @GeneratedValue
    private Integer categoryId;

    //    类目名字
    private String categoryName;

    //    类目编号
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
