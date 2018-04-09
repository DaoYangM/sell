package com.ye.sell.dto;

import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.OrderMaster;

import java.util.List;

public class OrderDTO extends OrderMaster {
    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
