package com.gmail.icyfiremario797.geminichad.api;


import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChadHandler {
    private static HttpClient client = HttpClient.newHttpClient();
    private static String testString = "{\"message\": \"TEST\"}";

    public static String sendMessage(String message) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5000/chat"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(testString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP Response Code: " + response.statusCode());
        System.out.println("Response from server: " + response.body());

        JSONObject jsonResponse = new JSONObject(response.body());

        String chadResponse = jsonResponse.getString("response");
        System.out.println("Chad's response: " + chadResponse);

        return chadResponse;
    }
}
