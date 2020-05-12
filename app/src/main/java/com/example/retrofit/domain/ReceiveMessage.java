package com.example.retrofit.domain;

public class ReceiveMessage {
    private boolean succeed;
    private int code;
    private String message;

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReceiveMessage{" +
                "succeed=" + succeed +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }



}
