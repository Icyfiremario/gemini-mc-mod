package com.gmail.icyfiremario797.geminichad.api.chad;

import com.google.gson.Gson;

import java.net.http.HttpClient;

public interface ChadInterface {


    static String Chad(PlayerMessage playerMessage) {
        final HttpClient client = HttpClient.newHttpClient();
        final Gson gson = new Gson();

        String formattedMessage = gson.toJson(playerMessage);

        return "";
    }
}
