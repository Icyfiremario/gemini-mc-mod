package com.gmail.icyfiremario797.geminichad.api.threading;

import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import com.mojang.brigadier.Command;
import net.minecraft.network.chat.Component;
import net.minecraft.client.player.LocalPlayer;

import java.util.Objects;

public class ThreadedReset extends ThreadedCommand {
    private LocalPlayer player;

    @Override
    public void run() {
        try {
            PlayerMessage playerMessage = new PlayerMessage();

            playerMessage.setPlayerName(player.getScoreboardName());
            playerMessage.setReset(true);

            int success = ChadHandler.resetChad(playerMessage);

            if (success == -1) {
                player.displayClientMessage(Component.translatable("commands.geminichad.reset.playernotfound", player.getScoreboardName()), false);
                return;
            }

            if (success != Command.SINGLE_SUCCESS) {
                player.displayClientMessage(Component.translatable("commands.geminichad.reset.failure"), false);
                return;
            }

            player.displayClientMessage(Component.translatable("commands.geminichad.reset.success"), false);

        } catch (Exception e) {
            if (Objects.equals(e.toString(), "java.net.ConnectException")) {
                player.displayClientMessage(Component.translatable("commands.geminichad.common.servernotfound"), false);
            }
            else {
                player.displayClientMessage(Component.literal(e.toString()), false);
            }
        }
    }

    public void setPlayer(LocalPlayer player) {
        this.player = player;
    }
}
