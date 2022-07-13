package com.collabothon2022.pedalpower.external.api;

public class ExchangeEntry {

    private int pointValue;
    private String labelText;
    private String base64TicketImg;

    public ExchangeEntry(int pointValue, String labelText, String base64TicketImg) {
        this.pointValue = pointValue;
        this.labelText = labelText;
        this.base64TicketImg = base64TicketImg;
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

    public String getBase64TicketImg() {
        return base64TicketImg;
    }

    public void setBase64TicketImg(String base64TicketImg) {
        this.base64TicketImg = base64TicketImg;
    }
}
