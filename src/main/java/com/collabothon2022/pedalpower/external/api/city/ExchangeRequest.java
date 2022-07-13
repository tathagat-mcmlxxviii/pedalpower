package com.collabothon2022.pedalpower.external.api.city;

public class ExchangeRequest {
    private final int userId;
    private final String buyUrl;

    public ExchangeRequest(int userId, String buyUrl) {
        this.userId = userId;
        this.buyUrl = buyUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getBuyUrl() {
        return buyUrl;
    }
}
