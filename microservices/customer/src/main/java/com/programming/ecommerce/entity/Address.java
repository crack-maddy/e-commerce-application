package com.programming.ecommerce.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
