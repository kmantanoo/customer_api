package com.example.customer_api.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Could not find customer with id=" + customerId);
    }
}
