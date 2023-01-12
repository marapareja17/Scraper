package org.example.scraper;

import org.example.Main;
import org.example.model.Comment;
import org.example.model.Location;
import org.example.model.Rating;
import org.example.model.Service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebScraper {
    //public String hotelName = "gran-via";
    private final String url = "https://www.booking.com/hotel/es/" + Main.getName() + ".es.html";
    private final String commentsUrl = "https://www.booking.com/reviews/es/hotel/" + Main.getName() + ".es.html";


    public static ArrayList<Location> hotelLocations = new ArrayList<>();
    public static ArrayList<Service> hotelServices = new ArrayList<>();
    public static ArrayList<Rating> hotelRatings = new ArrayList<>();
    public static ArrayList<Comment> hotelComments = new ArrayList<>();

    public static ArrayList<Location> getHotelLocations() {
        return hotelLocations;
    }

    public static ArrayList<Service> getHotelServices() {
        return hotelServices;
    }

    public static ArrayList<Rating> getHotelRatings() {
        return hotelRatings;
    }

    public static ArrayList<Comment> getHotelComments() {
        return hotelComments;
    }

    public void scraper() {
        // Compruebo si me da un 200 al hacer la petición
        if (getStatusConnectionCode(url) == 200 && getStatusConnectionCode(commentsUrl) == 200) {

            // Obtengo el HTML de la web en un objeto Documento
            Document page = getHtmlDocument(url);
            Document commentsPage = getHtmlDocument(commentsUrl);

            // Busco todos los datos que necesito:

            // Localización
            Element location = page.select("span.hp_address_subtitle").first();
            assert location != null;
            Location newLocation = new Location(Main.getName(), location.text());
            hotelLocations.add(newLocation);


            //System.out.println(newLocation.getHotelName() + newLocation.getLocation());
            /*System.out.println("-----Location-----\n" + location.text());*/

            // Servicios
            //System.out.println("\n-----Services----- ");
            Elements services = page.select("div.hotel-facilities-group");
            Map<String, String> servicesMap = new HashMap<>();

            for (Element service : services) {
                String serviceName = service.select("div.bui-title__text.hotel-facilities-group__title-text").text();
                String service1 = service.select("ul.bui-list.bui-list--text.bui-list--icon.hotel-facilities-group__list").text();
                String service2 = service.select("div.bui-spacer--medium.hotel-facilities-group__policy").text();
                String allService = service1 + service2;
                servicesMap.put(serviceName, allService);
            }

            Service newService = new Service(Main.getName(), servicesMap);
            hotelServices.add(newService);
            //System.out.println(newService.getServicesMap());

            /*for (Map.Entry<String, String> entry : servicesMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }*/


            // Ratings
            //System.out.println("\n-----Rating-----");
            Map<Object, Object> ratingsMap = new HashMap<>();
            Elements rateName = page.select("span.c-score-bar__title");
            Elements ratings = page.select("span.c-score-bar__score");
            ArrayList rateNamesList = (ArrayList) rateName.eachText();
            ArrayList ratingsList = (ArrayList) ratings.eachText();

            for (int i = 0; i < rateNamesList.size(); i++) {
                ratingsMap.put(rateNamesList.get(i), ratingsList.get(i));
                //System.out.println(rateNamesList.get(i) + ": " + ratingsList.get(i));
            }

            Rating newRating = new Rating(Main.getName(), ratingsMap);
            hotelRatings.add(newRating);
            //System.out.println(newRating.getRatingMap());

            // Comentarios
            //System.out.println("\n-----Comments-----");

            ArrayList<String> namesList = new ArrayList<>();
            ArrayList<String> reviewTitleList = new ArrayList<>();
            ArrayList<String> reviewScoreList = new ArrayList<>();
            ArrayList<String> positiveReviewsList = new ArrayList<>();
            ArrayList<String> negativeReviewsList = new ArrayList<>();

            Elements reviewerName = commentsPage.select("p.reviewer_name");
            String name;
            for (Element element : reviewerName) {
                name = element.text();
                namesList.add(name);
                //System.out.println(name);
            }

            Elements commentsTitle = commentsPage.select("div.review_item_review_header");
            String reviewTitle;
            String reviewScore;
            for (Element title : commentsTitle) {
                reviewTitle = title.select("div.review_item_header_content").text();
                reviewTitleList.add(reviewTitle);
                reviewScore = title.select("div.review_item_header_score_container").text();
                reviewScoreList.add(reviewScore);
                //System.out.println(reviewScore);
            }

            Elements comments = commentsPage.select("div.review_item_review_content");
            String positive;
            String negative;
            for (Element comment : comments) {
                positive = comment.select("p.review_pos").text();
                positiveReviewsList.add(positive);
                negative = comment.select("p.review_neg").text();
                negativeReviewsList.add(negative);
                //System.out.println(negative.replace("\n", ""));
            }
            for (int i = 0; i < namesList.size(); i++) {
                Comment newComment = new Comment(Main.getName(),
                        namesList.get(i),
                        reviewTitleList.get(i),
                        reviewScoreList.get(i),
                        positiveReviewsList.get(i),
                        negativeReviewsList.get(i));
                hotelComments.add(newComment);
            }

            //Comment newComment = new Comment(hotelName, name, reviewTitle, reviewScore, positive, negative);
            //hotelComments.add(newComment);

            /*for (int i = 0; i < commentList.size(); i++) {
                //System.out.println(commentList.get(i).toString());
            }*/

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
        }
    }


    /** Este método comprueba el Status code de la respuesta que recibe al hacer la petición*/
    public static int getStatusConnectionCode(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Chrome/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        assert response != null;
        return response.statusCode();
    }

    /** Este método devuelve un objeto de tipo Document con el contenido del HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup*/
    public static Document getHtmlDocument(String url) {

        Document page = null;

        try {
            page = Jsoup.connect(url).userAgent("Chrome/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }

        return page;
    }
}
