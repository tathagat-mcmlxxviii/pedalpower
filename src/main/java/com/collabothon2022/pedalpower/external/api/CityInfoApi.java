package com.collabothon2022.pedalpower.external.api;

import com.collabothon2022.pedalpower.persistence.model.City;

import java.util.List;

public interface CityInfoApi {
    List<ExchangeEntry> getExchangeOptionList();

    ExchangeResponse executeExchange(ExchangeRequest exchangeRequest);

    int getOneKilometerPointsValue(City city);
}
