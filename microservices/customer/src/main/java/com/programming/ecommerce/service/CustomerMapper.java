package com.programming.ecommerce.service;

import com.programming.ecommerce.dto.CustomerRequest;
import com.programming.ecommerce.dto.CustomerResponse;
import com.programming.ecommerce.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
