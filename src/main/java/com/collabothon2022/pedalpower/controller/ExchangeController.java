package com.collabothon2022.pedalpower.controller;

import com.collabothon2022.pedalpower.external.api.city.CityInfoApi;
import com.collabothon2022.pedalpower.external.api.city.CityInfoApiStub;
import com.collabothon2022.pedalpower.external.api.city.ExchangeEntry;
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
    	// TODO - use city instead of null
        return cityInfoApi.getExchangeOptionList(null);
    }
}
