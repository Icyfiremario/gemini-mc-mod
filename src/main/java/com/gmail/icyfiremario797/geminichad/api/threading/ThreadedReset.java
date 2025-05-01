package com.gmail.icyfiremario797.geminichad.api.threading;

import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import com.mojang.brigadier.Command;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class ThreadedReset extends ThreadedCommand {
    private Player player;

    @Override
    public void run() {
        try {
            PlayerMessage playerMessage = new PlayerMessage();

            playerMessage.setPlayerName(player.getScoreboardName());
            playerMessage.setReset(true);

            int success = ChadHandler.resetChad(playerMessage);

            if (success != Command.SINGLE_SUCCESS) {
                player.sendSystemMessage(Component.literal("AI failed to reset!"));
                return;
            }

            player.sendSystemMessage(Component.literal("AI was reset."));

        } catch (Exception e) {
            if (Objects.equals(e.toString(), "java.net.ConnectException")) {
                player.sendSystemMessage(Component.literal("Server not found!"));
            }
            else {
                player.sendSystemMessage(Component.literal(e.toString()));
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
