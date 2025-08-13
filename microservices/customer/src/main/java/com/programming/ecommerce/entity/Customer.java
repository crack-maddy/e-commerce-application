package com.programming.ecommerce.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
