package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta2 {
    public static void main(String[] args) {
        System.out.println(serieId());
        System.out.println(getUrlPoster());
    }

        // PRIMER METODO PARA SELECCIONAR EL ID DE LA SERIE
        public static String serieId() {
            String seriesId = "";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/idbyTitle/Dragon%20Ball/"))
                        .header("X-RapidAPI-Key", API.API_KEY)
                        .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonResponse = new JSONObject(response.body());
                System.out.println(jsonResponse.toString(2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return seriesId;
        }

        // YA OBTENIDO EL ID DE LA SERIE FILTRAMOS POR ID PARA OBTENER LA INFORMACION RELACIONADA A ESA SERIE.
        public static JSONObject getUrlPoster() {
            JSONObject resultObject = new JSONObject();
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/tt0280249/"))
                        .header("X-RapidAPI-Key", API.API_KEY)
                        .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonResponse = new JSONObject(response.body());
                int startYear = jsonResponse.getJSONObject("results").getInt("start_year");
                String bannerUrl = jsonResponse.getJSONObject("results").getString("banner");

                System.out.println("start_year: " + startYear);
                System.out.print("banner: " +  bannerUrl);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultObject;
        }
}

