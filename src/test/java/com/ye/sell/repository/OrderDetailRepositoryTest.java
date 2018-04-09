package com.ye.sell.repository;

import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("123");

        assertNotEquals(0, orderDetailList.size());
    }

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("2");
        orderDetail.setOrderId("123");

        ProductInfo productInfo = productInfoRepository.findById("123456").get();
        orderDetail.setProductId(productInfo.getProductId());
        orderDetail.setProductName(productInfo.getProductName());
        orderDetail.setProductId(productInfo.getProductId());
        orderDetail.setProductPrice(productInfo.getProductPrice());
        orderDetail.setProductQuantity(1);

        assertNotNull(orderDetailRepository.save(orderDetail));
    }
}