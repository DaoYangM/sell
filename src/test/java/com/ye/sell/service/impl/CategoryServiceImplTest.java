package com.ye.sell.service.impl;

import com.ye.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findById() {
        assertNotNull(categoryService.findById(1));
    }

    @Test
    public void findAll() {
        assertNotEquals(0, categoryService.findAll().size());
    }

    @Test
    public void findByCategoryTypeIn() {
        assertNotEquals(0, categoryService.findByCategoryTypeIn(new ArrayList<Integer>(){{
            add(1);
        }}).size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = categoryService.findById(1);
        productCategory.setCategoryName("test222");
        assertNotNull(categoryService.save(productCategory));
    }
}