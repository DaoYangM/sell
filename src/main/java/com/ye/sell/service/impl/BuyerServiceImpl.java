package com.ye.sell.service.impl;

import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.service.BuyerService;
import com.ye.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    private OrderService orderService;

    @Autowired
    public BuyerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOwn(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        return orderService.cancel(checkOwn(openId, orderId));
    }

    @Override
    public OrderDTO checkOwn(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("[openId不一致]错误");
            throw new SellException(ExceptionEnum.INCONSISTENT_OPEN_ID);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finishedOrder(String openId, String orderId) {
        return orderService.finished(checkOwn(openId, orderId));
    }
}
