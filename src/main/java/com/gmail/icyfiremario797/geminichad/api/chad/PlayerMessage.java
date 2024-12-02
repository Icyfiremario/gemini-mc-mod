package com.gmail.icyfiremario797.geminichad.api.chad;

public class PlayerMessage {
    private String PlayerName;
    private String PlayerMessage;
    private boolean reset;

    public void setPlayerName(String playerName) {
        this.PlayerName = playerName;
    }

    public void setPlayerMessage(String playerMessage) {
        this.PlayerMessage = playerMessage;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public String getPlayerMessage() {
        return PlayerMessage;
    }

    public boolean getReset() {
        return reset;
    }
}
