package com.collabothon2022.pedalpower.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/trip")
public class TripController {

    @GetMapping
    public String test(){
        return "TripController";
    }
}
