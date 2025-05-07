package com.gmail.icyfiremario797.geminichad.api.threading;

import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

import java.util.Objects;

public class ThreadedTellChad extends ThreadedCommand {
    private String message;
    private LocalPlayer player;

    @Override
    public void run() {
        try {
            PlayerMessage pMessage = new PlayerMessage();
            pMessage.setPlayerName(player.getScoreboardName());
            pMessage.setPlayerMessage(message);

            String response;

            response = ChadHandler.sendMessage(pMessage);


            player.displayClientMessage(Component.literal(String.format("%s: %s", pMessage.getPlayerName(), message)), false);
            player.displayClientMessage(Component.literal(String.format("Chad: %s", response)), false);
        } catch (Exception e) {
            if (Objects.equals(e.toString(), "java.net.ConnectException")) {
                player.displayClientMessage(Component.translatable("commands.geminichad.common.servernotfound"), false);
            }
            else {
                player.displayClientMessage(Component.literal(e.toString()), false);
            }
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPlayer(LocalPlayer player) {
        this.player = player;
    }
}
