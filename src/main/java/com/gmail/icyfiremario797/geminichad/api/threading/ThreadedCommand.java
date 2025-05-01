package com.gmail.icyfiremario797.geminichad.api.threading;

public class ThreadedCommand extends Thread {

    public void run() {
        System.out.printf("%s does not have a run function%n", this.getName());
    }
}
