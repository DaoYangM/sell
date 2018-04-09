package com.ye.sell.service.impl;

import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.CartDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.enums.ProductStatusEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.repository.ProductInfoRepository;
import com.ye.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductInfoRepository productInfoRepository;

    @Autowired
    public ProductServiceImpl(ProductInfoRepository productInfoRepository) {
        this.productInfoRepository = productInfoRepository;
    }

    @Override
    public ProductInfo findById(String id) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(id);
        return productInfo.orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            ProductInfo productInfo = this.findById(cartDTO.getProductId());
            Integer deCount = productInfo.getProductStock() - cartDTO.getCount();
            if (deCount >= 0) {
                productInfo.setProductStock(deCount);
                productInfoRepository.save(productInfo);
            } else
                throw new SellException(ExceptionEnum.STOCK);
        });
    }
}
