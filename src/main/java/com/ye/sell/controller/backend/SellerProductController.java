package com.ye.sell.controller.backend;

import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.exception.SellException;
import com.ye.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(page - 1, size));

        Map<String, Object> map = new HashMap<>();
        map.put("productInfoPage", productInfoPage);

        return new ModelAndView("product/list", map);
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
