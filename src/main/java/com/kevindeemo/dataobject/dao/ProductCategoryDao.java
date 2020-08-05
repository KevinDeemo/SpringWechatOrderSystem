package com.kevindeemo.dataobject.dao;

import com.kevindeemo.dataobject.ProductCategory;
import com.kevindeemo.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

/*
* mybatis的dao层
* */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByObject(ProductCategory productCategory){
        return mapper.insertByObject(productCategory);
    }
}
