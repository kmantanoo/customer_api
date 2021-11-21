package com.example.customer_api.services;

import com.example.customer_api.models.jpa.Customer;
import com.example.customer_api.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerUserDetailsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerUserDetailsService customerUserDetailsService;

    @Test
    public void testLoadByUsername_Success() {
        when(customerRepository.findByUsername("username")).thenReturn(new Customer("username",
                "password",
                "address",
                "phone",
                "refCode",
                "memberType",
                Long.valueOf(10000)));

        UserDetails customerDetail = customerUserDetailsService.loadUserByUsername("username");
        assertEquals("username", customerDetail.getUsername());
    }

    @Test
    public void testLoadByUsername_Fail() {
        when(customerRepository.findByUsername(anyString())).thenReturn(null);

        UsernameNotFoundException thrown = assertThrows(UsernameNotFoundException.class, () -> {
            customerUserDetailsService.loadUserByUsername("username");
        });

        assertTrue("User username not found.".equals(thrown.getMessage()));
    }
}
