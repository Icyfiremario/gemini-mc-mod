package com.gmail.icyfiremario797.geminichad.api.threading;

import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.io.IOException;
import java.util.Objects;

public class ThreadedTellChad extends ThreadedCommand {
    private String message;
    private Player player;

    @Override
    public void run() {
        try {
            PlayerMessage pMessage = new PlayerMessage();
            pMessage.setPlayerName(player.getScoreboardName());
            pMessage.setPlayerMessage(message);

            String response;

            LogUtils.getLogger().info("Attempting to send threaded message");

            response = ChadHandler.sendMessage(pMessage);

            player.sendSystemMessage(Component.literal(String.format("%s: %s", pMessage.getPlayerName(), message)));
            player.sendSystemMessage(Component.literal(String.format("Chad: %s", response)));
        } catch (Exception e) {
            if (Objects.equals(e.toString(), "java.net.ConnectException")) {
                player.sendSystemMessage(Component.literal("Server not found!"));
            }
            else {
                player.sendSystemMessage(Component.literal(e.toString()));
            }
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
