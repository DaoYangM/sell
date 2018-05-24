package com.ye.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    @NotEmpty(message = "productName不能为空")
    private String productName;

    @NotNull(message = "productPrice不能为空")
    private BigDecimal productPrice;

    @NotNull(message = "productStock不能为空")
    private Integer productStock;

    @NotEmpty(message = "productDescription不能为空")
    private String productDescription;

    @NotEmpty(message = "productIcon不能为空")
    private String productIcon;

    @NotNull(message = "categoryType不能为空")
    private Integer categoryType;
}
