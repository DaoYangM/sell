package com.ye.sell.repository;

import com.ye.sell.dataobject.OrderMaster;
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
    private OrderMasterRepository orderMasterRepository;

    private String openId = "123";

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("124");
        orderMaster.setBuyerName("test");
        orderMaster.setBuyerPhone("1234567");
        orderMaster.setBuyerAddress("test");
        orderMaster.setBuyerOpenid(openId);
        orderMaster.setOrderAmount(new BigDecimal(123));

        assertNotNull(orderMasterRepository.save(orderMaster));
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 10);
        Page<OrderMaster> orderMaster = orderMasterRepository.findByBuyerOpenid(openId, request);
        assertNotNull(orderMaster.getTotalElements());

    }
}