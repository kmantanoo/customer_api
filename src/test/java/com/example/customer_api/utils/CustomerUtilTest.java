package com.example.customer_api.utils;

import com.example.customer_api.models.api.RegisterRequest;
import com.example.customer_api.models.jpa.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerUtilTest {
    @Test
    public void testGetRefCode() {
        String phone = "123456789";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = df.format(new Date(System.currentTimeMillis()));

        assertEquals(formattedDate+"6789", CustomerUtil.getRefCode(phone));
    }

    @Test
    public void testGetMemberType() {
        assertEquals("Platinum", CustomerUtil.getMemberTypeBySalary(Long.valueOf(50001)));
        assertEquals("Gold", CustomerUtil.getMemberTypeBySalary(Long.valueOf(50000)));
        assertEquals("Gold", CustomerUtil.getMemberTypeBySalary(Long.valueOf(30000)));
        assertEquals("Silver", CustomerUtil.getMemberTypeBySalary(Long.valueOf(29999)));
    }

    @Test
    public void testNewCustomerInstance() {
        Customer customer = CustomerUtil.newInstanceOf("username",
                "password",
                "address",
                "phone",
                Long.valueOf(10000));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertEquals("username", customer.getUsername());
        assertEquals("address", customer.getAddress());
        assertEquals("phone", customer.getPhone());
        assertEquals(Long.valueOf(10000), customer.getSalary());
    }

    @Test
    public void testNewCustomerInstanceFromRegisterRequest() {
        Customer customer = CustomerUtil.newInstanceFromRegisterRequest(new RegisterRequest("username",
                "password",
                "address",
                "phone",
                Long.valueOf(10000)));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertEquals("username", customer.getUsername());
        assertEquals("address", customer.getAddress());
        assertEquals("phone", customer.getPhone());
        assertEquals(Long.valueOf(10000), customer.getSalary());
    }
}
