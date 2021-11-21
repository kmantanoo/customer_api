package com.example.customer_api.models.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^0[0-9]{9}$", message = "must starts with '0' and followed by 9 digits.")
    private String phone;

    @Min(15000)
    @NotNull
    private Long salary;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String address, String phone, Long salary) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "RegisterRequest{username=" + this.username + ", password=" + this.password + ", address='" + this.address + "', phone=" + this.phone +
                ", salary=" + this.salary + "}";
    }
}
