package com.gmail.icyfiremario797.geminichad.common.command;

import com.gmail.icyfiremario797.geminichad.Geminichad;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TestCommand {
    private static final com.gmail.icyfiremario797.geminichad.api.threading.TestCommand tCommand = new com.gmail.icyfiremario797.geminichad.api.threading.TestCommand();
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("testcommand").executes(commandContext -> execute(commandContext.getSource())));
    }

    private static int execute(CommandSourceStack command) {
        if (tCommand.isAlive()) {
            LogUtils.getLogger().debug("Thread is active. Canceling command");
            return 0;
        } else {
            Geminichad.commandScheduler.ScheduleCommands(tCommand);
        }
        return Command.SINGLE_SUCCESS;
    }
}
