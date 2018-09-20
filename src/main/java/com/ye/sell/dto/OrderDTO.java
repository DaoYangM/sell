package com.ye.sell.dto;

import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.OrderMaster;
import com.ye.sell.enums.OrderStatusEnum;
import com.ye.sell.enums.PayStatusEnum;
import com.ye.sell.utils.EnumUtil;

import java.util.ArrayList;
import java.util.List;

public class
OrderDTO extends OrderMaster {
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public PayStatusEnum getPayStatusEnum(Integer code) {
        return EnumUtil.getByCode(code, PayStatusEnum.class);
    }

    public OrderStatusEnum getOrderStatusEnum(Integer code) {
        return EnumUtil.getByCode(code, OrderStatusEnum.class);
    }
}
