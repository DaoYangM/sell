package com.ye.sell.service.impl;

import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.OrderMaster;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.OrderStatusEnum;
import com.ye.sell.enums.PayStatusEnum;
import com.ye.sell.repository.OrderMasterRepository;
import com.ye.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("test");
        orderDTO.setBuyerPhone("1234567890");
        orderDTO.setBuyerAddress("test");
        orderDTO.setBuyerOpenid("123456");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(1);

        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123457");
        orderDetail2.setProductQuantity(2);

        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);

        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1523281167222914472");
        assertNotNull(orderDTO);
    }

    @Test
    public void findAll() {
        String buyerId = "123456";
        PageRequest pageRequest = PageRequest.of(0, 10);
        assertNotEquals(0, orderService.findAll(buyerId, pageRequest).getTotalElements());
    }

    @Test
    public void findAllByAdmin() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        assertNotEquals(0, orderService.findAll(pageRequest).getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1523281167222914472");
        OrderDTO orderDTOUpdate = orderService.cancel(orderDTO);
        assertEquals(OrderStatusEnum.CANCEL.getCode(), orderDTOUpdate.getOrderStatus());
    }

    @Test
    public void finished() {
        OrderDTO orderDTO = orderService.findOne("1523281167222914472");
        assertEquals(OrderStatusEnum.FINISHED.getCode(), orderService.finished(orderDTO).getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1523281167222914472");
        assertEquals(PayStatusEnum.SUCCESS.getCode(), orderService.paid(orderDTO).getPayStatus());
    }
}