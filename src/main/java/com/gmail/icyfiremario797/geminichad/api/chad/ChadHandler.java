package com.gmail.icyfiremario797.geminichad.api.chad;


import com.google.common.annotations.Beta;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChadHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    @Beta
    private static final String ServerURL = "http://127.0.0.1:5000/chat";
    @Deprecated
    private static final String testString = "{\"message\": \"TEST\"}";
    private static final Gson gson = new Gson();

    public static String sendMessage(String message) throws IOException, InterruptedException {

        String formattedMessage = String.format("{\"message\": \"%s\"}", message);
        System.out.println(formattedMessage);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ServerURL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(formattedMessage))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println("HTTP Response Code: " + response.statusCode());
        System.out.println("Response from server: " + response.body());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        return chadResponse.getResponse();
    }
}
