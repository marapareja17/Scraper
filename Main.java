package org.example;

import org.example.controller.ApiRequest;
import org.example.controller.WebScraper;

import java.util.Scanner;

public class Main {
    private static String name;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                \nBienvenido al programa, este realiza un scraping en java de datos de booking
                como la localización de un hotel, los servicios que ofrece, el rating que ha
                conseguido y los comentarios que han dejado los usuarios.
                """);

        System.out.println("""
                A partir de estos datos he puesto a tu disposición un Web Service mediante el
                cuál puedes solicitar estos datos con peticiones "Get" siguiendo el siguiente formato:

                1. Para obtener información de la ubicación de un hotel: /hotels/:name/location
                2. Para obtener información de los servicios de un hotel: /hotels/:name/services
                3. Para obtener las valoraciones de un hotel: /hotels/:name/ratings
                4. Para obtener los comentarios de un hotel: /hotels/:name/comments
                """);

        System.out.println("""
                Para poder comenzar necesito que me proporciones el nombre del hotel del que quieres obtener los datos.
                Por ejemplo: gran-via
                """);

        System.out.println("Nombre del hotel: ");
        name = scan.next();
        System.out.println("\nAhora si, empecemos...");
        System.out.println("\n\tRecogiendo datos...\n");

        new WebScraper().scraper();
        new ApiRequest().request();

        System.out.println("\n¡Listo! ya puede consultar los datos.");
    }
    public static String getName() {
        return name;
    }
}