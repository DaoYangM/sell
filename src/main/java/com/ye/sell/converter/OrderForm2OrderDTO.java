package com.ye.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());

        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("[购物车对象转换]错误, String={}", orderForm.getItems());
            throw new SellException(ExceptionEnum.PARAMETER_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
