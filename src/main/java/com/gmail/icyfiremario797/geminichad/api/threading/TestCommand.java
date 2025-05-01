package com.gmail.icyfiremario797.geminichad.api.threading;

import com.mojang.logging.LogUtils;

public class TestCommand extends ThreadedCommand {
    public void run() {
        LogUtils.getLogger().info("I'm a test command!");
    }
}
