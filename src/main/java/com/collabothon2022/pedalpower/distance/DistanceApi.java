package com.collabothon2022.pedalpower.distance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DistanceApi {
    double getDistanceInKm(String gpsPointData);

    default List<String[]> parseGpsCoordinates(String gpsPointData){
        List<String[]> result = new ArrayList<>();
        String[] coordinates = gpsPointData.split(";");
        for (String coordinate : coordinates) {
            String[] latLon = coordinate.split(",");
            result.add(latLon);
        }
        return result;
    }
}
