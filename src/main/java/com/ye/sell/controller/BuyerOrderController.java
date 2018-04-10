package com.ye.sell.controller;

import com.ye.sell.converter.OrderForm2OrderDTO;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.form.OrderForm;
import com.ye.sell.service.BuyerService;
import com.ye.sell.service.OrderService;
import com.ye.sell.utils.ResultVoUtils;
import com.ye.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    private OrderService orderService;
    private BuyerService buyerService;

    @Autowired
    public BuyerOrderController(OrderService orderService, BuyerService buyerService) {
        this.orderService = orderService;
        this.buyerService = buyerService;
    }

    @PostMapping("/create")
    public ResultVo create(@Validated OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单参数错误]");
            throw new SellException(bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[购物车不能为空]");
            throw new SellException(ExceptionEnum.SHOPPING_CART_CANNOT_BE_EMPTY);
        }

        Map<String, String> result = new HashMap<>();
        result.put("orderId", orderService.create(orderDTO).getOrderId());

        return ResultVoUtils.success(result);
    }

    @GetMapping("/list")
    public ResultVo list(@RequestParam("openId") String openId,
                         @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize ) {

        if (StringUtils.isEmpty(openId)) {
            log.error("[openId]不能为空]");
            throw new SellException(ExceptionEnum.OPENID_CANNOT_BE_EMPTY);
        }

        Page<OrderDTO> orderDTOPage = orderService.findAll(openId, PageRequest.of(pageNum, pageSize));

        return ResultVoUtils.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVo detail(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[openId]不能为空]");
            throw new SellException(ExceptionEnum.OPENID_CANNOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(orderId)) {
            log.error("[orderId]不能为空]");
            throw new SellException(ExceptionEnum.ORDER_ID_CANNOT_BE_EMPTY);
        }

        return ResultVoUtils.success(buyerService.findOrderOne(openId,orderId));
    }

    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[openId]不能为空]");
            throw new SellException(ExceptionEnum.OPENID_CANNOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(orderId)) {
            log.error("[orderId]不能为空]");
            throw new SellException(ExceptionEnum.ORDER_ID_CANNOT_BE_EMPTY);
        }

        return ResultVoUtils.success(buyerService.cancelOrder(openId,orderId));
    }

    @PostMapping("/finished")
    public ResultVo finished(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[openId]不能为空]");
            throw new SellException(ExceptionEnum.OPENID_CANNOT_BE_EMPTY);
        }

        if (StringUtils.isEmpty(orderId)) {
            log.error("[orderId]不能为空]");
            throw new SellException(ExceptionEnum.ORDER_ID_CANNOT_BE_EMPTY);
        }

        return ResultVoUtils.success(buyerService.finishedOrder(openId, orderId));
    }
    // paying order
}
