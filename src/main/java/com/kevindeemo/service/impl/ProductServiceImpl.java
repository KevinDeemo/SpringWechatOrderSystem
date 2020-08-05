package com.kevindeemo.service.impl;

import com.kevindeemo.dataobject.ProductInfo;
import com.kevindeemo.dto.CartDTO;
import com.kevindeemo.enums.ProductStatusEnum;
import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.exception.SellException;
import com.kevindeemo.repository.ProductInfoRepository;
import com.kevindeemo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
//@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository repository;

    @Override
//    key需要保持一致
    @Cacheable(key = "123")
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @CachePut(key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo==null) throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo==null) throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result<0) throw new SellException(ResultEnum.PRODUCT_STOCK__NOT_ENOUGH);
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo==null){
            log.error("【商品上架】查无此商品 productId = {}", productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productInfo==null){
            log.error("【商品下架】查无此商品 productId = {}", productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }

}
