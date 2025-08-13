package com.programming.ecommerce.dto;

import com.programming.ecommerce.entity.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
