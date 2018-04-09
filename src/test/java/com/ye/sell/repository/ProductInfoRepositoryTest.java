package com.ye.sell.repository;

import com.ye.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository   productInfoRepository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("123456");
        productInfo.setProductName("Test");
        productInfo.setProductPrice(new BigDecimal(111));
        productInfo.setProductStock(111);
        productInfo.setProductDescription("Test");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        assertNotNull(productInfoRepository.save(productInfo));
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(0);
        assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findById() {
        Optional<ProductInfo> productInfo = productInfoRepository.findById("123456");
        assertNotNull(productInfo);
    }
}