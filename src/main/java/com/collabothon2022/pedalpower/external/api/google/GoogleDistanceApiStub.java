package com.collabothon2022.pedalpower.external.api.google;

public class GoogleDistanceApiStub implements GoogleDistanceApi {

    @Override
    public double getDistanceInKm(String gpsPointData){
        return 20.1;
    }
}
