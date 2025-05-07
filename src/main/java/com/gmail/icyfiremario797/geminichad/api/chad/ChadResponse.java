package com.gmail.icyfiremario797.geminichad.api.chad;

public class ChadResponse {
    private String response;
    private String error;
    private int errorCode;

    private boolean reset;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

    public int getErrorCode() {
        return errorCode;
    }

    public boolean getReset() {
        return reset;
    }
}
