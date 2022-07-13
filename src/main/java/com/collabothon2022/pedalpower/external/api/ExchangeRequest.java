package com.collabothon2022.pedalpower.external.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExchangeRequest {
    private Long userId;
    private String buyUrl;
}
