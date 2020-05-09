package com.example.retrofit.webSocket;

import android.util.Log;

import com.example.retrofit.domain.MyEntry;
import com.example.retrofit.domain.ReceiveMessage;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class WebSocketTest extends WebSocketClient {


    public WebSocketTest(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println(shake.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        Gson gson = new Gson();
        ReceiveMessage message1=gson.fromJson(message,ReceiveMessage.class);
        System.out.println(message1.toString());
        if (message1.getCode()==1) {

        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("关闭");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("发生错误");
    }
}
