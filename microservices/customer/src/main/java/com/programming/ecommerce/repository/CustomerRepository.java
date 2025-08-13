package com.programming.ecommerce.repository;

import com.programming.ecommerce.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
