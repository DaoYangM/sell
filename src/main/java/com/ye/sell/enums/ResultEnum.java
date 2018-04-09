package com.ye.sell.enums;

public enum  ResultEnum {

    SUCCESS(0, "success"),
    ERROR(10, "error");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
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
