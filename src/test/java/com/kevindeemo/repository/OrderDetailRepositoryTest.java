package com.kevindeemo.repository;

import com.kevindeemo.dataobject.OrderDetail;
import com.kevindeemo.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234");
        orderDetail.setOrderId("12345");
        orderDetail.setProductId("123456");
        orderDetail.setProductIcon("www.xxxxx.jpg");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(7.5));
        orderDetail.setProductQuantity(3);

        Assert.assertNotNull(repository.save(orderDetail));
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("12345");
        Assert.assertNotEquals(0, result.size());
    }
}