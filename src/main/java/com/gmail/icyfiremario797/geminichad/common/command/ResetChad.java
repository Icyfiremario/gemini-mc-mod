package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedCommand;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedReset;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class ResetChad {
    private static final ThreadedReset tCommand = new ThreadedReset();
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetchad").executes(ResetChad::run));
    }

    public static int execute(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player player) {
            try {
                PlayerMessage playerMessage = new PlayerMessage();

                playerMessage.setPlayerName(player.getScoreboardName());
                playerMessage.setReset(true);

                int success = ChadHandler.resetChad(playerMessage);

                if (success != Command.SINGLE_SUCCESS) {
                    player.sendSystemMessage(Component.literal("AI failed to reset!"));
                    return 0;
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

        return Command.SINGLE_SUCCESS;
    }

    private static int run(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player player) {
            tCommand.setPlayer(player);
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        } else {
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
