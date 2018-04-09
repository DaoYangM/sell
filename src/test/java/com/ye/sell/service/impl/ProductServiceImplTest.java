package com.ye.sell.service.impl;

import com.ye.sell.dataobject.ProductInfo;
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
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findById() {
        assertNotNull(productService.findById("123456"));
    }

    @Test
    public void findUpAll() {
        assertNotEquals(0, productService.findUpAll().size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage);
        assertNotNull(productInfoPage);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("1234567");
        productInfo.setProductName("Test2");
        productInfo.setProductPrice(new BigDecimal(111));
        productInfo.setProductStock(1121);
        productInfo.setProductDescription("Test");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        assertNotNull(productService.save(productInfo));
    }
}