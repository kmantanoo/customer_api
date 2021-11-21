package com.example.customer_api.models.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(nullable = false, length = 12)
    private String refCode;

    @Column(nullable = false)
    private String memberType;

    @Column(nullable = false)
    private Long salary;

    public Customer() {
    }

    public Customer(String username, String password, String address, String phone, String refCode, String memberType, Long salary) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.refCode = refCode;
        this.memberType = memberType;
        this.salary = salary;
    }

    public Long getId() {
        return id;
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

    @Override
    public int hashCode() {
        return Objects.hash(this.username, this.password, this.address, this.phone, this.refCode, this.memberType,
                this.salary);
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", username=" + this.username + ", address='" + this.address + "', phone=" + this.phone +
                ", refCode=" + this.refCode + ", memberType=" + this.memberType + ", salary=" + this.salary + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Customer)) return false;

        Customer c = (Customer) o;
        return Objects.equals(this.id, c.id) &&
                Objects.equals(this.username, c.username) &&
                Objects.equals(this.password, c.password) &&
                Objects.equals(this.address, c.address) &&
                Objects.equals(this.phone, c.phone) &&
                Objects.equals(this.refCode, c.refCode) &&
                Objects.equals(this.memberType, c.memberType) &&
                Objects.equals(this.salary, c.salary);
    }
}
