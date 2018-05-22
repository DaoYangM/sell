package com.ye.sell.exception;

import com.ye.sell.enums.ExceptionEnum;

public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SellException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
