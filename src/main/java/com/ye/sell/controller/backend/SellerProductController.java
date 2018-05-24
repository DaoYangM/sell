package com.ye.sell.controller.backend;

import com.ye.sell.dataobject.ProductCategory;
import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.exception.SellException;
import com.ye.sell.form.ProductForm;
import com.ye.sell.service.CategoryService;
import com.ye.sell.service.ProductService;
import com.ye.sell.utils.KeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/seller/product")
public class SellerProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> map = new HashMap<>();
        map.put("productInfoPage", productInfoPage);

        return new ModelAndView("product/list", map);
    }

    // 查看修改商品详情
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId) {

        Map<String, Object> map = new HashMap<>();

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findById(productId);

            if (productInfo != null) {
                map.put("productInfo", productInfo);
            }
        }

        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    public Object save(@Validated ProductForm productForm, BindingResult result) {
        Map<String, Object> map = new HashMap<>();

        if (result.hasErrors()) {
            map.put("msg", result.getFieldError().getDefaultMessage());
            map.put("code", "danger");

            return new ModelAndView("common/result", map);
        }

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productForm, productInfo);
        if (StringUtils.isEmpty(productForm.getProductId()))
            productInfo.setProductId(KeyUtils.getUniqueKey());

        productInfo.setProductStatus(0);
        productService.save(productInfo);

        map.put("msg", "操作成功！");
        map.put("code", "success");
        return "redirect:/seller/product/index?productId=" + productInfo.getProductId();
    }


    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();

        try {
            ProductInfo productInfo = productService.onSale(productId);
                map.put("msg", "商品上架成功！");
                map.put("code", "success");
                return new ModelAndView("common/result", map);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("code", "danger");
            return new ModelAndView("common/result", map);
        }
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            ProductInfo productInfo = productService.offSale(productId);
            map.put("msg", "商品下架成功！");
            map.put("code", "success");
            return new ModelAndView("common/result", map);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("code", "danger");
            return new ModelAndView("common/result", map);
        }
    }
}
