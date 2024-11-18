package com.gmail.icyfiremario797.geminichad.api.chad;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChadHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String testString = "{\"message\": \"TEST\"}";
    private static final Gson gson = new Gson();

    public static String sendMessage(String message) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5000/chat"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(testString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP Response Code: " + response.statusCode());
        System.out.println("Response from server: " + response.body());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        return chadResponse.getResponse();
    }
}
