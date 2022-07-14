package com.collabothon2022.pedalpower.external.api.google;

import org.springframework.stereotype.Component;

@Component
public class GoogleDistanceApiStub implements GoogleDistanceApi {

    @Override
    public int getDistanceInKm(String gpsPointData){
        return 20;
    }
}
