package com.ye.sell.service.impl;

import com.ye.sell.dataobject.SellerInfo;
import com.ye.sell.repository.SellerInfoRepository;
import com.ye.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerServiceImpl implements SellerService {

    private SellerInfoRepository sellerInfoRepository;

    @Autowired
    public void setSellerInfoRepository(SellerInfoRepository sellerInfoRepository) {
        this.sellerInfoRepository = sellerInfoRepository;
    }

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        return sellerInfoRepository.findByOpenid(openid);
    }
}
