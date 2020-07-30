package com.kevindeemo.service.impl;

import com.kevindeemo.dto.OrderDTO;
import com.kevindeemo.service.OrderService;
import com.kevindeemo.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    PayService payService;

    @Autowired
    OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1595573422247396571");
        payService.create(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1596066305304333704");
        payService.refund(orderDTO);
    }
}