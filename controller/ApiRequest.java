package org.example.controller;

import com.google.gson.Gson;
import org.example.Main;
import org.example.model.Comment;
import org.example.model.Location;
import org.example.model.Rating;
import org.example.model.Service;
import org.example.scraper.WebScraper;

import java.util.List;
import java.util.stream.Collectors;
import static spark.Spark.get;


public class ApiRequest {
    public void request() {


        get("/hotels/:name/location", (req, res) -> {
            String hotelName = req.params(":name");
            res.type("application/json");
            List<Location> filteredLocations = getLocation(hotelName);
            return toJson(filteredLocations);
        });

        get("/hotels/:name/services", (req, res) -> {
            String hotelName = req.params(":name");
            res.type("application/json");
            List<Service> filteredServices = getService(hotelName);
            return toJson(filteredServices);
        });

        get("/hotels/:name/ratings", (req, res) -> {
            String hotelName = req.params(":name");
            res.type("application/json");
            List<Rating> filteredRatings = getRatings(hotelName);
            return toJson(filteredRatings);
        });

        get("/hotels/:name/comments", (req, res) -> {
            String hotelName = req.params(":name");
            res.type("application/json");
            List<Comment> filteredComments = getComments(hotelName);
            return toJson(filteredComments);
        });

    }

        private static List<Location> getLocation(String hotelName){
           return WebScraper.getHotelLocations().stream()
                   .filter(h -> h.getHotelName().equals(Main.getName()))
                   .collect(Collectors.toList());
        }

        private static List<Service> getService(String hotelName){
            return WebScraper.getHotelServices().stream()
                    .filter(h -> h.getHotelName().equals(Main.getName()))
                    .collect(Collectors.toList());
        }

        private static List<Rating> getRatings(String hotelName){
            return WebScraper.getHotelRatings().stream()
                    .filter(h -> h.getHotelName().equals(Main.getName()))
                    .collect(Collectors.toList());
        }

        private static List<Comment> getComments(String hotelName){
            return WebScraper.getHotelComments().stream()
                    .filter(h -> h.getHotelName().equals(Main.getName()))
                    .collect(Collectors.toList());
        }

    private static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}
