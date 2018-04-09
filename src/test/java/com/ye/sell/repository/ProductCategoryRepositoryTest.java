package com.ye.sell.repository;

import com.ye.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    public void save() {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
        if (productCategory.isPresent()) {
            productCategory.get().setCategoryId(1);
            productCategory.get().setCategoryName("testUpdate2");
            productCategory.get().setCategoryType(1);
            productCategoryRepository.save(productCategory.get());
        }
    }

    @Test
    public void ProductCategoryRepository() {
        List<Integer> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(1);

        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        System.out.println(productCategories);
    }
}