package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta4 {

    public static void main(String[] args) {

        System.out.println(getMovieName());
        System.out.println(getGokuActor());
        System.out.println(getActorInfo());
    }

    public static String getMovieName() {
        String moviename = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/imdb_id/byTitle/Dragonball%20Evolution/"))
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
        return moviename;
    }

    public static String getGokuActor() {
        String actor = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/id/tt1098327/cast/"))
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
        return actor;
    }

    public static JSONObject getActorInfo() {
        JSONObject resultObject = new JSONObject();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/actor/id/nm0154226/"))
                    .header("X-RapidAPI-Key", API.API_KEY)
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());

            String cumple = jsonResponse.getJSONObject("results").getString("birth_date");
            String signo = jsonResponse.getJSONObject("results").getString("star_sign");

            System.out.println("birth_date: " + cumple);
            System.out.print("star_sign: " +  signo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}
