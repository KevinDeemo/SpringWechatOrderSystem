package com.kevindeemo.service.impl;

import com.kevindeemo.dataobject.SellerInfo;
import com.kevindeemo.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    private static final String openId = "abc";

    @Autowired
    SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(openId);
        Assert.assertEquals(openId, result.getOpenid());
    }
}