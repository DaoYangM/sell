package com.ye.sell.service.impl;

import com.ye.sell.dto.OrderDTO;
import com.ye.sell.service.OrderService;
import com.ye.sell.service.PayService;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    private PayService payService;

    private OrderService orderService;

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1523368317080846046");
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDTO orderDTO = orderService.findOne("1526914039270705662");
        payService.refund(orderDTO);
    }
}