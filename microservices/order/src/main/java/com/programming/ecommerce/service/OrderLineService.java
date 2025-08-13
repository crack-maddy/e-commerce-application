package com.programming.ecommerce.service;

import com.programming.ecommerce.dto.request.OrderLineRequest;
import com.programming.ecommerce.mapper.OrderLineMapper;
import com.programming.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.mapToOrderLine(request);
        return orderLineRepository.save(order).getId();
    }
}
