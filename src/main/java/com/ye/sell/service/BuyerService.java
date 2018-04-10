package com.ye.sell.service;

import com.ye.sell.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openId, String orderId);

    OrderDTO cancelOrder(String openId, String orderId);

    OrderDTO checkOwn(String openId, String orderId);

    OrderDTO finishedOrder(String openId, String orderId);
}
