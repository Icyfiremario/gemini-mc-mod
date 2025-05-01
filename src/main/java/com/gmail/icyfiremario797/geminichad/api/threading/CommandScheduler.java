package com.gmail.icyfiremario797.geminichad.api.threading;

import java.util.ArrayList;

public class CommandScheduler {

    private final ArrayList<ThreadedCommand> commands = new ArrayList<>();

    public void ScheduleCommands(ThreadedCommand command) {
        commands.add(command);
    }

    public void RunCommands() {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).start(); // Use run instead of start to prevent crashes from weird thread state stuff
            commands.remove(i); // Pop command from list after running
        }
    }

}
