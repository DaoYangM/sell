package com.ye.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openId不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenId() {
        return this.openid;
    }
}
