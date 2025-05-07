package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedTellChad;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class TellChad {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tellchad").then(Commands.argument("message", StringArgumentType.greedyString()).executes(commandContext -> run(commandContext.getSource(), StringArgumentType.getString(commandContext, "message")))));
    }

    private static int run(CommandSourceStack command, String msg) {
        if (command.getEntity() instanceof LocalPlayer player) {
            ThreadedTellChad tCommand = new ThreadedTellChad();
            tCommand.setMessage(msg);
            tCommand.setPlayer(player);
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        } else {
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
