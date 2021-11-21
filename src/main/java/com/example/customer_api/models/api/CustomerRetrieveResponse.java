package com.example.customer_api.models.api;

public class CustomerRetrieveResponse {
    private Long id;
    private String username;
    private String address;
    private String phone;
    private String refCode;
    private String memberType;
    private Long salary;

    public CustomerRetrieveResponse() {
    }

    public CustomerRetrieveResponse(Long id, String username, String address, String phone, String refCode, String memberType, Long salary) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.refCode = refCode;
        this.memberType = memberType;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
