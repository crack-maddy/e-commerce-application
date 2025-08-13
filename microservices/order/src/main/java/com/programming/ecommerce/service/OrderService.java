package com.programming.ecommerce.service;

import com.programming.ecommerce.client.CustomerClient;
import com.programming.ecommerce.client.ProductClient;
import com.programming.ecommerce.dto.request.OrderLineRequest;
import com.programming.ecommerce.dto.request.OrderRequest;
import com.programming.ecommerce.dto.request.PurchaseRequest;
import com.programming.ecommerce.exception.BusinessException;
import com.programming.ecommerce.mapper.OrderMapper;
import com.programming.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public String createOrder(OrderRequest request) {
        //check the customer (open-feign)
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exists with the provided ID: " + request.customerId())
                );

        //purchase the product -> product-ms (RestTemplate)
        this.productClient.purchaseProducts(request.products());

        //persist order
        var order = this.orderRepository.save(orderMapper.toOrder(request));

        //persist order-lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start the payment process

        //send the order confirmation --> notification-ms (kafka)

        return null;
    }
}
