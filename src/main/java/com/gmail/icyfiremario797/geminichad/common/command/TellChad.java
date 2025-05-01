package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.gmail.icyfiremario797.geminichad.api.chad.ChadHandler;
import com.gmail.icyfiremario797.geminichad.api.chad.PlayerMessage;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedTellChad;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class TellChad {
    private static final ThreadedTellChad tCommand = new ThreadedTellChad();
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tellchad").then(Commands.argument("message", StringArgumentType.greedyString()).executes(commandContext -> run(commandContext.getSource(), StringArgumentType.getString(commandContext, "message")))));
    }

    private static int execute(CommandSourceStack command, String msg) {
        if (command.getEntity() instanceof Player player) {
            try {
                PlayerMessage pMessage = new PlayerMessage();
                pMessage.setPlayerName(player.getScoreboardName());
                pMessage.setPlayerMessage(msg);

                String response;


                response = ChadHandler.sendMessage(pMessage);


                player.sendSystemMessage(Component.literal(String.format("%s: %s", pMessage.getPlayerName(), msg)));
                player.sendSystemMessage(Component.literal(String.format("Chad: %s", response)));

            } catch (Exception e) {
                if (Objects.equals(e.toString(), "java.net.ConnectException")) {
                    player.sendSystemMessage(Component.literal("Server not found!"));
                }
                else {
                    player.sendSystemMessage(Component.literal(e.toString()));
                }
            }

            //player.sendSystemMessage(Component.literal(msg));
        }
        else
        {
            if (command.getEntity() != null) {
                command.getEntity().sendSystemMessage(Component.literal("Entity is not player"));
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int run(CommandSourceStack command, String msg) {
        if (command.getEntity() instanceof Player player) {
            tCommand.setMessage(msg);
            tCommand.setPlayer(player);
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        } else {
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
