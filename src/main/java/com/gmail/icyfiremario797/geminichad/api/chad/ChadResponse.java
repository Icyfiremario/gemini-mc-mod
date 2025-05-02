package com.gmail.icyfiremario797.geminichad.api.chad;

public class ChadResponse {
    private String response;
    private String error;
    private boolean reset;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    public boolean getReset() {
        return reset;
    }
}
