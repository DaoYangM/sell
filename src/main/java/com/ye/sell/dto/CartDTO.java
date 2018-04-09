package com.ye.sell.dto;

import lombok.Data;

@Data
public class CartDTO {

    private String productId;

    private Integer count;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer count) {
        this.productId = productId;
        this.count = count;
    }
}
