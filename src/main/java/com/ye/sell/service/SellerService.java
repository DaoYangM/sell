package com.ye.sell.service;

import com.ye.sell.dataobject.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
