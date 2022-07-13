package com.collabothon2022.pedalpower.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/exchange/history")
public class ExchangeHistoryController {

    @GetMapping
    public String test(){
        return "ExchangeHistoryController";
    }
}
