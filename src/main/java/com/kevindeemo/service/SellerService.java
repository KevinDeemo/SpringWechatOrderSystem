package com.kevindeemo.service;

import com.kevindeemo.dataobject.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
