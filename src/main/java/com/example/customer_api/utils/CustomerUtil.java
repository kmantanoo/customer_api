package com.example.customer_api.utils;

import com.example.customer_api.models.api.RegisterRequest;
import com.example.customer_api.models.jpa.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerUtil {

    private static final DateFormat df = new SimpleDateFormat("yyyyMMdd");

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Customer newInstanceFromRegisterRequest(RegisterRequest registerRequest) {
        return newInstanceOf(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getAddress()
        , registerRequest.getPhone(), registerRequest.getSalary());
    }

    public static Customer newInstanceOf(String username,
                                         String password,
                                         String address,
                                         String phone,
                                         Long salary) {
        return new Customer(username,
                encoder.encode(password),
                address,
                phone,
                getRefCode(phone),
                getMemberTypeBySalary(salary),
                salary);
    }

    public static String getRefCode(String phone) {
        return df.format(new Date(System.currentTimeMillis())) + phone.substring(phone.length() - 4);
    }

    public static String getMemberTypeBySalary(Long salary) {
        if (salary > 50000) return "Platinum";
        else if (salary <= 50000 && salary >= 30000) return "Gold";
        else return "Silver";
    }
}
