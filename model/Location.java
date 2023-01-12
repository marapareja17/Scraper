package org.example.model;

public class Location {
    private final String hotelName;
    private final String location;

    public Location(String hotelName, String location) {
        this.hotelName = hotelName;
        this.location = location;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }
}
