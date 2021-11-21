package com.example.customer_api.repo;


import com.example.customer_api.models.jpa.Customer;
import com.example.customer_api.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer customer = new Customer();
        customer.setUsername("tommy");
        customer.setPassword(encoder.encode("tommy"));
        customer.setAddress("Birmingham");
        customer.setPhone("0812345678");
        customer.setRefCode("refcode");
        customer.setMemberType("gold");
        customer.setSalary(Long.valueOf(50000));

        Customer savedCustomer = repo.save(customer);

        Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());

        assertThat(customer.getUsername()).isEqualTo(existCustomer.getUsername());

    }
}
