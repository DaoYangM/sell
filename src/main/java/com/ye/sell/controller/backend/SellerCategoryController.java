package com.ye.sell.controller.backend;

import com.ye.sell.dataobject.ProductCategory;
import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.form.CategoryForm;
import com.ye.sell.form.ProductForm;
import com.ye.sell.service.CategoryService;
import com.ye.sell.utils.KeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false)
                                          Integer categoryId, Map<String, Object> map) {

        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findById(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public Object save(@Validated CategoryForm categoryForm, BindingResult result) {
        Map<String, Object> map = new HashMap<>();

        if (result.hasErrors()) {
            map.put("msg", result.getFieldError().getDefaultMessage());
            map.put("code", "danger");

            return new ModelAndView("common/result", map);
        }

        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(categoryForm, productCategory);

        categoryService.save(productCategory);

        map.put("msg", "操作成功！");
        map.put("code", "success");
        return "redirect:/seller/category/list";
    }
}
