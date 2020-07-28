package com.kevindeemo.service;

import com.kevindeemo.dataobject.ProductInfo;
import com.kevindeemo.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    ProductInfo findOne(String productId);

    //    查询所有上架的商品列表
    List<ProductInfo> findUpAll();

    //    分页
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

//    加库存
    void increaseStock(List<CartDTO> cartDTOList);

//    减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
