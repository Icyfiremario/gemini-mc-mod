package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.gmail.icyfiremario797.geminichad.api.threading.ThreadedReset;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class ResetChad {
    //private static final ThreadedReset tCommand = new ThreadedReset();
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetchad").executes(ResetChad::run));
    }

    private static int run(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player player) {
            ThreadedReset tCommand = new ThreadedReset();
            tCommand.setPlayer(player);
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        } else {
            return 0;
        }

        return Command.SINGLE_SUCCESS;
    }
}
