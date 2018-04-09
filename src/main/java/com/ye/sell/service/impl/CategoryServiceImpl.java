package com.ye.sell.service.impl;

import com.ye.sell.dataobject.ProductCategory;
import com.ye.sell.repository.ProductCategoryRepository;
import com.ye.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory findById(Integer id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        return productCategory.orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeIds) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeIds);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
