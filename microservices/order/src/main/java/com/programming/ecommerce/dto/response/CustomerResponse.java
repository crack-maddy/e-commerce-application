package com.programming.ecommerce.dto.response;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
