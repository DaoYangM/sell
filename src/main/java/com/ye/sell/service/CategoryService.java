package com.ye.sell.service;

import com.ye.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findById(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds);

    ProductCategory save(ProductCategory productCategory);
}
