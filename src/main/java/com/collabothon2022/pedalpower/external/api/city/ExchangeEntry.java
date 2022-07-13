package com.collabothon2022.pedalpower.external.api.city;

public class ExchangeEntry {

    private int id;
    private int pointValue;
    private String labelText;
    private String buyUrl;

    public ExchangeEntry(int id,int pointValue, String labelText, String buyUrl) {
        this.id = id;
        this.pointValue = pointValue;
        this.labelText = labelText;
        this.buyUrl = buyUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }
}
