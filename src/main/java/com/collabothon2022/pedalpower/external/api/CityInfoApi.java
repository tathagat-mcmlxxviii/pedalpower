package com.collabothon2022.pedalpower.external.api;

import java.util.List;

import com.collabothon2022.pedalpower.persistence.model.City;

public interface CityInfoApi {
    List<ExchangeEntry> getExchangeOptionList(City city);

    ExchangeResponse executeExchange(ExchangeRequest exchangeRequest);
}
