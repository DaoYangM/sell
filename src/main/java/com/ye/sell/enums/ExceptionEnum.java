package com.ye.sell.enums;

public enum ExceptionEnum {

    PRODUCT_DOES_NOT_EXIST(50, "商品不存在"),
    STOCK(60, "库存不足"),
    ORDER_DOES_NOT_EXIST(100, "订单不存在"),
    ORDER_DETAIL_DOES_NOT_EXIST(110, "订单详情不存在"),
    ORDER_STATUS_ERROR(111, "订单状态错误"),
    ORDER_STATUS_UPDATE_ERROR(112, "订单状态更新错误");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
