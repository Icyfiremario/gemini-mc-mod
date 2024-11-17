package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.api.ChadHandler;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class TellChad {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tellchad").then(Commands.argument("message", StringArgumentType.greedyString()).executes(commandContext -> execute(commandContext.getSource(), StringArgumentType.getString(commandContext, "message")))));
    }

    private static int execute(CommandSourceStack command, String msg) {
        if (command.getEntity() instanceof Player player) {
            try {
                String response = ChadHandler.sendMessage("test");
                player.sendSystemMessage(Component.literal(response));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //player.sendSystemMessage(Component.literal(msg));
        }

        return Command.SINGLE_SUCCESS;
    }
}
