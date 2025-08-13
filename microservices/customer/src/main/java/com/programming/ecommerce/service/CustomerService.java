package com.programming.ecommerce.service;

import com.programming.ecommerce.dto.CustomerRequest;
import com.programming.ecommerce.dto.CustomerResponse;
import com.programming.ecommerce.entity.Customer;
import com.programming.ecommerce.exception.CustomerNotFoundException;
import com.programming.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(
                        () -> new CustomerNotFoundException(
                                String.format("Cannot update customer:: No customer found with the provided id:: %s", request.id())
                        )
                );
        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(
                        () -> new CustomerNotFoundException(String.format("No customer found with the provided ID:: %s",customerId))
                );
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
