package com.ye.sell.repository;

import com.ye.sell.dataobject.SellerInfo;
import com.ye.sell.utils.KeyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();

        sellerInfo.setId(KeyUtils.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("yedeyang");
        sellerInfo.setOpenid("abc");

        assertNotNull(sellerInfoRepository.save(sellerInfo));
    }

    @Test
    public void findByOpenid() {
        assertNotNull(sellerInfoRepository.findByOpenid("abc"));
    }
}