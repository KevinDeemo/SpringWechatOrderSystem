package com.kevindeemo.repository;

import com.kevindeemo.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("kevin1");
        orderMaster.setBuyerPhone("123456789");
        orderMaster.setBuyerAddress("2663 Orchard Ave");
        orderMaster.setBuyerOpenId("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.3));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenId() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMaster> result = repository.findByBuyerOpenId(OPENID, request);
        Assert.assertNotEquals(0, result.getContent().size());
    }
}