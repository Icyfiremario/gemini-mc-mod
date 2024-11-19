package com.gmail.icyfiremario797.geminichad.api.chad;

public class ChadResponse {
    private String response;
    private boolean reset;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getResponse() {
        return response;
    }

    public boolean getReset() {
        return reset;
    }
}
