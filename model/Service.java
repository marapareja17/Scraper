package org.example.model;

import java.util.Map;

public class Service {
    private final String hotelName;
    private final Map servicesMap;


    public Service(String hotelName, Map servicesMap) {
        this.hotelName = hotelName;
        this.servicesMap = servicesMap;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Map getServicesMap() {
        return servicesMap;
    }
}
