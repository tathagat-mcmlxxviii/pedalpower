package com.collabothon2022.pedalpower.external.api;

public class ExchangeRequest {
    private final int id;
    private final String buyUrl;

    public ExchangeRequest(int id, String buyUrl) {
        this.id = id;
        this.buyUrl = buyUrl;
    }

    public int getId() {
        return id;
    }

    public String getBuyUrl() {
        return buyUrl;
    }
}
