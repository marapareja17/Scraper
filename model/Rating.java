package org.example.model;

import java.util.Map;

public class Rating {
    private final String hotelName;
    private final Map ratingMap;

    public Rating(String hotelName, Map ratingMap) {
        this.hotelName = hotelName;
        this.ratingMap = ratingMap;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Map getRatingMap() {
        return ratingMap;
    }
}
