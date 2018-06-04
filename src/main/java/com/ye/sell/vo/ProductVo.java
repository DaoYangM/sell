package com.ye.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ye.sell.dataobject.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVo implements Serializable {

    private static final long serialVersionUID = -3300549537015187817L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
