package com.gmail.icyfiremario797.geminichad.api.chad;


import com.gmail.icyfiremario797.geminichad.config.GeminichadConfig;
import com.google.gson.Gson;
import com.mojang.brigadier.Command;
import com.mojang.logging.LogUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class ChadHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String ServerURL = GeminichadConfig.CHAD_SERVER_URL.get();
    private static final Gson gson = new Gson();

    public static String sendMessage(PlayerMessage pMessage) throws IOException, InterruptedException {
        String formattedMessage = gson.toJson(pMessage);
        LogUtils.getLogger().info(formattedMessage);
        LogUtils.getLogger().info(String.format("Server: %s", GeminichadConfig.CHAD_SERVER_URL.get()));

        HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(String.format("%s/chat", GeminichadConfig.CHAD_SERVER_URL.get())))
               .header("Content-Type", "application/json")
               .POST(HttpRequest.BodyPublishers.ofString(formattedMessage))
               .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        return chadResponse.getResponse();
    }

    public static int resetChad(PlayerMessage pMessage) throws IOException, InterruptedException {
        String formattedMessage = gson.toJson(pMessage);
        LogUtils.getLogger().info(formattedMessage);
        LogUtils.getLogger().info(String.format("Server: %s", GeminichadConfig.CHAD_SERVER_URL.get()));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/reset", GeminichadConfig.CHAD_SERVER_URL.get())))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(formattedMessage))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ChadResponse chadResponse = gson.fromJson(response.body(), ChadResponse.class);

        if (!chadResponse.getReset()) {
            LogUtils.getLogger().error(chadResponse.getError());

            if (Objects.equals(chadResponse.getError(), "Player not in instances!")) {
                return -1;
            }

            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
