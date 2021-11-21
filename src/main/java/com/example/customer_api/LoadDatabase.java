package com.example.customer_api;

import com.example.customer_api.models.jpa.Customer;
import com.example.customer_api.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//    @Bean
    public CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
//            String username, String password, String address, String phone, String refCode, String memberType, int salary
            log.info("Preloading " + repository.save(new Customer("tommyshelby", "tommy", "Birmingham", "66123", "2021112061234", "Platinum", Long.valueOf(400000))));
            log.info("Preloading " + repository.save(new Customer("jonathan", "jonathan", "Washington", "12345", "202111202345", "Gold", Long.valueOf(50000))));
        };
    }
}
