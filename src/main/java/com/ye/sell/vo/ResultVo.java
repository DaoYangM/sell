package com.ye.sell.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = -3895954577580798052L;

    private Integer code;

    private String msg;

    T data;
}
