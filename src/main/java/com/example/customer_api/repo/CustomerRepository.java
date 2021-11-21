package com.example.customer_api.repo;

import com.example.customer_api.models.jpa.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.username = ?1")
    public Customer findByUsername(String username);
}
