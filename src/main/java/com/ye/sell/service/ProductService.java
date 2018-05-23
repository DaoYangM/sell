package com.ye.sell.service;

import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findById(String id);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void decreaseStock(List<CartDTO> cartDTOList);

    void increaseStock(List<CartDTO> cartDTOList);

    ProductInfo onSale(String id);

    ProductInfo offSale(String id);
}
