package com.ye.sell.controller.portal;

import com.ye.sell.dataobject.ProductCategory;
import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.service.CategoryService;
import com.ye.sell.service.ProductService;
import com.ye.sell.utils.ResultVoUtils;
import com.ye.sell.vo.ProductInfoVo;
import com.ye.sell.vo.ProductVo;
import com.ye.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private ProductService productService;

    private CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResultVo list() {
        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryIdList =  productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryIdList);

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory category : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(category.getCategoryName());
            productVo.setCategoryType(category.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (category.getCategoryId().equals(productInfo.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }

            productVo.setProductInfoVoList(productInfoVoList);

            productVoList.add(productVo);
        }

        return ResultVoUtils.success(productVoList);
    }
}
