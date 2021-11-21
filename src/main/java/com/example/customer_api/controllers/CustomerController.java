package com.example.customer_api.controllers;

import com.example.customer_api.exceptions.CustomerNotFoundException;
import com.example.customer_api.models.api.CustomerRetrieveResponse;
import com.example.customer_api.models.api.RegisterRequest;
import com.example.customer_api.models.api.RegisterResponse;
import com.example.customer_api.models.jpa.Customer;
import com.example.customer_api.repo.CustomerRepository;
import com.example.customer_api.services.CustomerUserDetailsService;
import com.example.customer_api.utils.CustomerUtil;
import com.example.customer_api.utils.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/customers")
    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public CustomerRetrieveResponse customerInfo(@PathVariable Long id) throws Exception {
        log.info("Find customer by customerId={}", id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return new CustomerRetrieveResponse(customer.getId(),
                customer.getUsername(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getRefCode(),
                customer.getMemberType(),
                customer.getSalary());
    }

    @PostMapping("/register")
    public ResponseEntity<?> regis(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        log.info("Received register request " + registerRequest);
        Customer customer = CustomerUtil.newInstanceFromRegisterRequest(registerRequest);

        log.debug("Persisting new customer to database");
        Customer savedCustomer = customerRepository.save(customer);

        final UserDetails userDetails = customerUserDetailsService.loadUserByUsername(savedCustomer.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new RegisterResponse(jwt, savedCustomer.getId()));
    }
}
