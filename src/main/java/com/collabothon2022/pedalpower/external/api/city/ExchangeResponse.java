package com.collabothon2022.pedalpower.external.api.city;

public class ExchangeResponse {
    private String base64TicketImg;

    public ExchangeResponse(String base64TicketImg) {
        this.base64TicketImg = base64TicketImg;
    }

    public String getBase64TicketImg() {
        return base64TicketImg;
    }

    public void setBase64TicketImg(String base64TicketImg) {
        this.base64TicketImg = base64TicketImg;
    }
}
