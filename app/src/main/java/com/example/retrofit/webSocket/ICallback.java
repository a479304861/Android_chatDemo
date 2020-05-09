package com.example.retrofit.webSocket;

public interface ICallback<T> {
        void onSuccess(T t);
        void onFail(String msg);
}
