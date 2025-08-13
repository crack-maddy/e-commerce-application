package com.programming.ecommerce.mapper;

import com.programming.ecommerce.dto.request.OrderLineRequest;
import com.programming.ecommerce.entity.Order;
import com.programming.ecommerce.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine mapToOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .build();
    }
}
