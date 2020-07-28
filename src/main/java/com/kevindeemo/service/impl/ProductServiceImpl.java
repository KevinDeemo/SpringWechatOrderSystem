package com.kevindeemo.service.impl;

import com.kevindeemo.dataobject.ProductInfo;
import com.kevindeemo.dto.CartDTO;
import com.kevindeemo.enums.ProductStatusEnum;
import com.kevindeemo.enums.ResultEnum;
import com.kevindeemo.exception.SellException;
import com.kevindeemo.repository.ProductInfoRepository;
import com.kevindeemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository repository;

    @Override
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

}
