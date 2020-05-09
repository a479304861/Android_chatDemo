package com.example.retrofit.domain;

public class BaseRespose {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "success=" + success +
                '}';
    }
}
