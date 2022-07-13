package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.external.api.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.CityInfoApiStub;
import com.collabothon2022.pedalpower.external.api.ExchangeEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeController {

    private final CityInfoApi cityInfoApi;

    public ExchangeController() {
        cityInfoApi = new CityInfoApiStub();
    }

    @GetMapping(value = "list", produces = "application/json")
    public List<ExchangeEntry> getExchangeOptionList(){
        return cityInfoApi.getExchangeOptionList();
    }
}
