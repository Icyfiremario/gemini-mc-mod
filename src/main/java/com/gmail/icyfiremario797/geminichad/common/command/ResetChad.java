package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedReset;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.logging.LogUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ResetChad {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetchad").executes(ResetChad::run));
    }

    private static int run(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof LocalPlayer player) {
            ThreadedReset tCommand = new ThreadedReset();
            tCommand.setPlayer(player);
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        } else {
            assert command.getSource().getEntity() != null;
            LogUtils.getLogger().error("Got entity type {}", command.getSource().getEntity().getName().getString());
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
