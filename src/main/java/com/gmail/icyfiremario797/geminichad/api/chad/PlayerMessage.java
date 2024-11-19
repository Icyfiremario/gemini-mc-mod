package com.gmail.icyfiremario797.geminichad.api.chad;

public class PlayerMessage {
    private String uName;
    private String msg;
    private boolean reset;

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getuName() {
        return uName;
    }

    public String getMsg() {
        return msg;
    }

    public boolean getReset() {
        return reset;
    }
}
