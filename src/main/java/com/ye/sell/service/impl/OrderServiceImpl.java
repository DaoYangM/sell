package com.ye.sell.service.impl;

import com.ye.sell.converter.OrderMaster2OrderDTOConverter;
import com.ye.sell.dataobject.OrderDetail;
import com.ye.sell.dataobject.OrderMaster;
import com.ye.sell.dataobject.ProductInfo;
import com.ye.sell.dto.CartDTO;
import com.ye.sell.dto.OrderDTO;
import com.ye.sell.enums.ExceptionEnum;
import com.ye.sell.enums.OrderStatusEnum;
import com.ye.sell.enums.PayStatusEnum;
import com.ye.sell.exception.SellException;
import com.ye.sell.repository.OrderDetailRepository;
import com.ye.sell.repository.OrderMasterRepository;
import com.ye.sell.service.OrderService;
import com.ye.sell.service.ProductService;
import com.ye.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
                throw new SellException(ExceptionEnum.PRODUCT_DOES_NOT_EXIST);

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
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if (!orderMaster.isPresent())
            throw new SellException(ExceptionEnum.ORDER_DOES_NOT_EXIST);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.get().getOrderId());
        if (orderDetailList.size() <= 0)
            throw new SellException(ExceptionEnum.ORDER_DETAIL_DOES_NOT_EXIST);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster.get(), orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        if (orderDTO.getOrderDetailList().isEmpty()) {
            log.error("[取消订单] 订单商品数量不正确, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[取消订单] 订单状态不正确, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster orderMasterUpdate = orderMasterRepository.save(orderMaster);
        if (!orderMasterUpdate.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
            log.error("[更新订单状态] 失败, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_UPDATE_ERROR);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productService.increaseStock(cartDTOList);

        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finished(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单] 失败, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_FINISHED_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[订单支付状态] 失败, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }

        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单支付] 失败, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_PAYMENT_ERROR);
        }

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
