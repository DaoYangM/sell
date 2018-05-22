package com.ye.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.exception.SellException;
import com.ye.sell.service.OrderService;
import com.ye.sell.service.PayService;
import com.ye.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ye.sell.enums.ExceptionEnum.WECHAT_NOTIFY_DIFFERENT_AMOUNT_OF_PAYMENT;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    private BestPayServiceImpl bestPayService;

    private OrderService orderService;

    @Autowired
    public void setBestPayService(BestPayServiceImpl bestPayService) {
        this.bestPayService = bestPayService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付 Request】 = {}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付 Response】 = {}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);

        log.info("【微信支付】异步通知 Response={}", JsonUtil.toJson(payResponse));

        // 查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
//        orderDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount())) != 0
        if (orderDTO.getOrderAmount().doubleValue() != payResponse.getOrderAmount()) {
            log.error("【微信支付】支付金额和订单金额不同 orderId={}", payResponse.getOrderId());
            throw new SellException(WECHAT_NOTIFY_DIFFERENT_AMOUNT_OF_PAYMENT);
        }

        orderService.paid(orderDTO);
        payResponse.getOrderId();
        log.info("【微信支付】 支付完成!");
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
