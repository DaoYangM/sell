package com.ye.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "categoryName不能为空")
    private String categoryName;

    @NotNull(message = "categoryType不能为空")
    private Integer categoryType;
}
