package com.example.customer_api.models.api;

public class RegisterResponse {
    private final String accessToken;

    private final Long customerId;

    public RegisterResponse(String accessToken, Long customerId) {
        this.accessToken = accessToken;
        this.customerId = customerId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
