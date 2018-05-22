package com.ye.sell.controller.backend;

import com.lly835.bestpay.rest.type.Get;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.exception.SellException;
import com.ye.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<OrderDTO> orderDTOPage = orderService.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> map = new HashMap<>();
        map.put("orderDTOPage", orderDTOPage);

        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               @RequestParam(value = "type", defaultValue = "cancel") String type) {

        Map<String, Object> map = new HashMap<>();

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            if (type.equals("cancel")) {
                orderService.cancel(orderDTO);
                map.put("msg", "取消订单成功！");
            } else if (type.equals("finished")){
                orderService.finished(orderDTO);
                map.put("msg", "完结订单成功！");
            }
            map.put("code", "success");
            return new ModelAndView("order/result", map);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("code", "danger");
            return new ModelAndView("order/result", map);
        }
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId) {

        Map<String, Object> map = new HashMap<>();

        OrderDTO orderDTO = orderService.findOne(orderId);

        map.put("orderDTO", orderDTO);

        return new ModelAndView("order/detail", map);
    }

}
