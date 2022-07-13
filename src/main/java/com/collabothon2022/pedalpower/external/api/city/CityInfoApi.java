package com.collabothon2022.pedalpower.external.api.city;

import java.util.List;

public interface CityInfoApi {
    List<ExchangeEntry> getExchangeOptionList();

    ExchangeResponse executeExchange(ExchangeRequest exchangeRequest);
}
