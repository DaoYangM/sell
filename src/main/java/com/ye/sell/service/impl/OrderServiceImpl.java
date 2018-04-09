package com.ye.sell.service.impl;

import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.OrderMaster;
import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.CartDTO;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.repository.OrderDetailRepository;
import com.ye.sell.repository.OrderMasterRepository;
import com.ye.sell.service.OrderService;
import com.ye.sell.service.ProductService;
import com.ye.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderMasterRepository orderMasterRepository;

    private ProductService productService;

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderServiceImpl(OrderMasterRepository orderMasterRepository,
                            ProductService productService,
                            OrderDetailRepository orderDetailRepository) {
        this.orderMasterRepository = orderMasterRepository;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtils.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            OrderDetail orderDetailNew = new OrderDetail();
            String detailId = KeyUtils.getUniqueKey();

            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            if (productInfo == null)
                throw new SellException(ExceptionEnum.ERROR);

            BigDecimal productPrice = productInfo.getProductPrice();
            orderAmount = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            orderDetailNew.setDetailId(detailId);
            orderDetailNew.setOrderId(orderId);
            orderDetailNew.setProductId(orderDetail.getProductId());
            orderDetailNew.setProductName(productInfo.getProductName());
            orderDetailNew.setProductPrice(productInfo.getProductPrice());
            orderDetailNew.setProductQuantity(orderDetail.getProductQuantity());
            orderDetailNew.setProductIcon(productInfo.getProductIcon());

            orderDetailRepository.save(orderDetailNew);
        }

        productService.decreaseStock(orderDTO.getOrderDetailList().stream().
                map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList()));

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findAll(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finished(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
