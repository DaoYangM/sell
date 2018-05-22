package com.ye.sell.controller.portal;

import com.lly835.bestpay.model.PayResponse;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.service.OrderService;
import com.ye.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    private OrderService orderService;

    private PayService payService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @GetMapping
    public ModelAndView pay(@RequestParam("openid") String openid,
                            @RequestParam("orderId") String orderId,
                            @RequestParam("returnUrl") String returnUrl,
                    Map<String, Object> map) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        log.info("openid={}", openid);

        if (orderDTO == null)
            throw new SellException(ExceptionEnum.ORDER_DOES_NOT_EXIST);

        orderDTO.setBuyerOpenid(openid);
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        try {
            returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) { payService.notify(notifyData);

        //返回微信处理结果;
        return new ModelAndView("pay/success");
    }
}
