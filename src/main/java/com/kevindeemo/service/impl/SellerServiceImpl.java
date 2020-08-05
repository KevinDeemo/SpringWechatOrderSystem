package com.kevindeemo.service.impl;

import com.kevindeemo.dataobject.SellerInfo;
import com.kevindeemo.repository.SellerInfoRepository;
import com.kevindeemo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
