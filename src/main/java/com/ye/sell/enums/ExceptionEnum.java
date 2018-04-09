package com.ye.sell.enums;

public enum ExceptionEnum {

    ERROR(50, "商品不存在"),
    STOCK(60, "库存不足");

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
