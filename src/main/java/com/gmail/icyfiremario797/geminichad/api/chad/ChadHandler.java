package com.gmail.icyfiremario797.geminichad.api.chad;


import com.gmail.icyfiremario797.geminichad.config.GeminichadConfig;
import com.google.common.annotations.Beta;
import com.google.gson.Gson;
import com.mojang.brigadier.Command;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChadHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    @Beta
    private static final String ServerURL = GeminichadConfig.CHAD_SERVER_URL.get();
    @Deprecated
    private static final String testString = "{\"message\": \"TEST\"}";
    private static final Gson gson = new Gson();

    public static String sendMessage(String message) throws IOException, InterruptedException {

        String formattedMessage = String.format("{\"message\": \"%s\"}", message);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/chat", ServerURL)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(formattedMessage))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        return chadResponse.getResponse();
    }

    public static int resetChad() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/reset", ServerURL)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        if (!chadResponse.getReset()) {
            return 0;
        }

        return Command.SINGLE_SUCCESS;

    }
}
