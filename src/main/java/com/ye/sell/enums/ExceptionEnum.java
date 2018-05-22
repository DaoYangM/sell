package com.ye.sell.enums;

public enum ExceptionEnum {

    PARAMETER_ERROR(30, "参数错误"),
    SHOPPING_CART_CANNOT_BE_EMPTY(31, "购物车不能为空"),
    OPENID_CANNOT_BE_EMPTY(32, "openId不能为空"),
    ORDER_ID_CANNOT_BE_EMPTY(33, "orderId不能为空"),
    PRODUCT_DOES_NOT_EXIST(50, "商品不存在"),
    STOCK(60, "库存不足"),
    ORDER_DOES_NOT_EXIST(100, "订单不存在"),
    ORDER_DETAIL_DOES_NOT_EXIST(110, "订单详情不存在"),
    ORDER_STATUS_ERROR(111, "订单状态错误"),
    ORDER_STATUS_UPDATE_ERROR(112, "订单状态更新错误"),
    ORDER_FINISHED_ERROR(113, "订单完结错误"),
    ORDER_PAYMENT_ERROR(114, "订单支付失败"),

    INCONSISTENT_OPEN_ID(200, "openId不一致"),
    WECAHT_MP_GET_ACCESSTOKEN_ERROR(301, "微信公众号获取ACCESS TOKEN错误"),
    WECHAT_NOTIFY_DIFFERENT_AMOUNT_OF_PAYMENT(302, "【微信支付】支付金额和订单金额不同");

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
